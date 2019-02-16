package com.richard.studies.reactive.tourismyapp.services;

import com.richard.studies.reactive.tourismyapp.models.CountryData;
import com.richard.studies.reactive.tourismyapp.models.Range;
import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;


public class MilanTourismStatsService {
    private Logger LOG = Logger.getLogger(this.getClass().getName());

    public void howManyNightsAllTouristHaveSpent(Flowable<CountryData> touristData) {
        final String MESSAGE_TEMPLATE = "HowManyNightsAllTouristHaveSpent";

        touristData.map(countryData -> countryData.getNights())
                .reduce((nights, acc) -> acc + nights)
                .subscribe((totalNights) ->  log(MESSAGE_TEMPLATE, totalNights));
    }

    public void listOfAllGeoAreas(Flowable<CountryData> touristData) {
        touristData.reduce(new TreeSet<>(), new BiFunction<Set<String>, CountryData, Set<String>>() {
            @Override
            public Set<String> apply(Set<String> geoAreas, CountryData countryData) throws Exception {
                geoAreas.add(countryData.getGeoArea());
                return geoAreas;
            }
        })
        .subscribe((geoAreas) -> log("ListOfAllGeoGroups", geoAreas));
    }

    public void listOfAllCountries(Flowable<CountryData> touristData) {
        touristData.reduce(new TreeSet<>(), new BiFunction<Set<String>, CountryData, Set<String>>() {
            @Override
            public Set<String> apply(Set<String> countries, CountryData countryData) throws Exception {
                countries.add(countryData.getCountry());
                return countries;
            }
        })
        .subscribe((countries) -> log("ListOfAllCountries", countries));
    }

    private <T> void log(String message, T result) {
        final String SEPARATOR = "--------------------------------------------";
        LOG.info(String.format("%s - %s: %s\n%s", Thread.currentThread().getName(), message, result, SEPARATOR));
    }

    public void howManyNightsACountryHasPassedBetweenRange(Flowable<CountryData> tourismData, String countryName, Range range) {
        final String MESSAGE = String.format("howManyNightsACountryHasPassedBetweenRange[%s][%s]", countryName, range);
        tourismData.filter((countryData) -> countryName.equals(countryData.getCountry()))
                    .filter((countryData -> range.in(countryData.getYear())))
                    .reduce(0, new BiFunction<Integer, CountryData, Integer>() {
                        @Override
                        public Integer apply(Integer acc, CountryData countryData) throws Exception {
                            return acc + countryData.getNights();
                        }
                    })
                    .subscribe((totalNights) -> log(MESSAGE, totalNights));
    }

    public void countryHasBeenVisited(Flowable<CountryData> touristData, String countryName) {
        final String MESSAGE = String.format("countryHasBeenVisited[%s]", countryName);
        touristData.any((countryData) -> countryName.equals(countryData.getCountry()))
                    .subscribe((result) -> log(MESSAGE, result));
    }
}
