package com.richard.studies.reactive.tourismyapp;

import com.richard.studies.reactive.tourismyapp.models.CountryData;
import com.richard.studies.reactive.tourismyapp.models.Range;
import com.richard.studies.reactive.tourismyapp.services.MilanTourismStatsService;
import com.richard.studies.reactive.tourismyapp.services.MilanTouristService;
import io.reactivex.Flowable;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Pais (filtro por ano especifico ou range)
 * 	Individual
 * 		* Pais com mais noites
 * 		* Grupo com mais noites
 * 	Lista
 * 		* 5 paises com mais visitas de um grupo (noites e media)
 * 		* 5 paises com mais visitas entre todos (noites e media)
 * 		* Grupos com mais visitas
 * 	Perguntas
 * 		* Quantas visitas um determinado pais fez
 * 		* Um determinado pais visitou?
 * 	Geral
 * 		* Quantos noites foram gastas por turistas
 * 		Ordem dos anos com mais turistas
 * 		* Recorde de turistas de um pais em um ano
 * 	Informacoes
 * 		* Todos os paises considerados
 * 		* Todos os grupos e seus paises
 *
 */
public class TourismApp {
    private static Range rangeBetween2000And2002 = new Range(Optional.ofNullable(2000), Optional.ofNullable(2002));

    public static void main(String[] args) throws Exception {
        final String MILANO_DATA_URL = "http://dati.comune.milano.it/dataset/14d4632c-44f2-4b03-8467-e9546b72942b/resource/4a53e761-437d-441e-a5a4-8ba1fdfb01bd/download/212_presenze-2000-2004_.csv";
        Flowable<CountryData> touristData = new MilanTouristService(MILANO_DATA_URL).getTouristData();

        MilanTourismStatsService milanTourismStatsService = new MilanTourismStatsService();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(() -> milanTourismStatsService.howManyNightsAllTouristHaveSpent(touristData));
        executorService.submit(() -> milanTourismStatsService.listOfAllGeoAreas(touristData));
        executorService.submit(() -> milanTourismStatsService.listOfAllCountries(touristData));
        executorService.submit(() ->
                milanTourismStatsService.howManyNightsACountryHasPassedBetweenRange(
                        touristData,
                        "Brasile",
                        rangeBetween2000And2002
                )
        );
        executorService.submit(() ->
                milanTourismStatsService.howManyNightsACountryHasPassedBetweenRange(
                        touristData,
                        "Cina",
                        rangeBetween2000And2002
                )
        );
        executorService.submit(() ->
                milanTourismStatsService.howManyNightsACountryHasPassedBetweenRange(
                        touristData,
                        "INVALID",
                        rangeBetween2000And2002
                )
        );


        executorService.submit(() -> milanTourismStatsService.countryHasBeenVisited(touristData, "Argentina"));
        executorService.submit(() -> milanTourismStatsService.countryHasBeenVisited(touristData, "Scozia"));

        executorService.submit(() -> milanTourismStatsService.howManyNightsACountryHasPassed(touristData, "Portogallo"));
        executorService.submit(() -> milanTourismStatsService.howManyNightsACountryHasPassed(touristData, "Cina"));
        executorService.submit(() -> milanTourismStatsService.howManyNightsACountryHasPassed(touristData, "Scozia"));

        executorService.submit(() -> milanTourismStatsService.yearWithMoreVisitorsByCountry(touristData, "Giappone"));
        executorService.submit(() -> milanTourismStatsService.yearWithMoreVisitorsByCountry(touristData, "Brasile"));
        executorService.submit(() -> milanTourismStatsService.yearWithMoreVisitorsByCountry(touristData, "Germania"));

        executorService.submit(() -> milanTourismStatsService.firstXCountryWithMoreNights(touristData, 10));
        executorService.submit(() -> milanTourismStatsService.geoAreasWithMoreNights(touristData));

        executorService.submit(() -> milanTourismStatsService.countriesWithMoreNightsByGeoarea(touristData, "EU 15"));
        executorService.submit(() -> milanTourismStatsService.countriesWithMoreNightsByGeoarea(touristData, "Asia"));
        executorService.submit(() -> milanTourismStatsService.countriesWithMoreNightsByGeoarea(touristData, "America Nord"));
        executorService.submit(() -> milanTourismStatsService.countriesWithMoreNightsByGeoarea(touristData, "America Latina"));

        executorService.submit(() -> milanTourismStatsService.yearsWithMoreVisitors(touristData));

//        with error :( executorService.submit(() -> milanTourismStatsService.countryWithMoreNights(touristData));

        executorService.shutdown();
    }
}
