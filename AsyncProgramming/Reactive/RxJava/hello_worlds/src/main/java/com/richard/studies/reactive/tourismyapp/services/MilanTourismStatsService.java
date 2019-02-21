package com.richard.studies.reactive.tourismyapp.services;

import com.richard.studies.reactive.tourismyapp.models.CountryData;
import com.richard.studies.reactive.tourismyapp.models.Range;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
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


    public void countryWithMoreNights(Flowable<CountryData> touristData) {
        // How to order after groupBy
//        touristData.groupBy(countryData -> countryData.getCountry())
//                .map(groupedByCountry -> groupedByCountry.collect(CountryWithMoreNights::new, (a, b) -> {
//                    a.country = b.getCountry();
//                    a.nights += b.getNights();
//                }))
//                .subscribe(abc -> {
//                    abc.to
//                            .subscribe(a -> System.out.println(a));
//                    System.out.println("abc: " + abc);
//                    abc.toFlowable().toList().subscribe(a -> {
//                        System.out.println("abc-subscribe: ");
//                        System.out.println(a);
//                    });
//                });
    }

    public void howManyNightsACountryHasPassed(Flowable<CountryData> touristData, String countryName) {
        final String MESSAGE = String.format("howManyNightsACountryHasPassed: %s has passed: ", countryName);
        touristData.filter((countryData) -> countryData.getCountry().equals(countryName))
                .map(countryData -> countryData.getNights())
                .reduce((countryNight, totalCountryNights) -> countryNight + totalCountryNights)
                .subscribe(result -> {
                    log(MESSAGE, result);
                });
    }

    public void yearWithMoreVisitorsByCountry(Flowable<CountryData> touristData, String countryName) {
        final String MESSAGE = String.format("yearWithMoreVisitorsByCountry[%s]", countryName);
        touristData.filter((countryData) -> countryData.getCountry().equals(countryName))
                .sorted((countryDataOne, countryDataTwo) -> countryDataTwo.getNights().compareTo(countryDataOne.getNights()))
                .firstElement()
                .subscribe(result -> log(MESSAGE, result));
    }


    private static class CountryWithMoreNights implements Comparable<CountryWithMoreNights> {
        private String country;
        private Integer nights = 0;

        @Override
        public String toString() {
            return "CountryWithMoreNights{" +
                    "country='" + country + '\'' +
                    ", nights=" + nights +
                    '}';
        }

        @Override
        public int compareTo(CountryWithMoreNights countryWithMoreNights) {
            return nights.compareTo(countryWithMoreNights.nights);
        }
    }
}
