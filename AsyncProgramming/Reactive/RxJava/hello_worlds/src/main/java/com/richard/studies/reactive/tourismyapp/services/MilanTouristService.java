package com.richard.studies.reactive.tourismyapp.services;

import com.richard.studies.reactive.tourismyapp.models.CountryData;
import io.reactivex.Flowable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MilanTouristService {

    private final String url;

    public MilanTouristService(String url) {
        this.url = url;
    }

    public Flowable<CountryData> getTouristData() {
        return Flowable.fromCallable(getTouristDataFromApi())
                        .map(this::toCsv)
                        .flatMap(this::csvRecordsToCountryData);
    }

    private Callable<String> getTouristDataFromApi() {
        return () -> Request.Get(url).execute().returnContent().asString();
    }

    private Flowable<CountryData> csvRecordsToCountryData(CSVParser csvRecords) {
        return Flowable.fromIterable(
                StreamSupport.stream(csvRecords.spliterator(), false)
                    .map(this::recordToCountryData)
                    .collect(Collectors.toList())
        );
    }

    private CountryData recordToCountryData(CSVRecord record) {
        return new CountryData(record.get(0), record.get(1),
                Integer.valueOf(record.get(2)), Integer.valueOf(record.get(3)));
    }

    private CSVParser toCsv(String tourismData) throws IOException {
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(';');
        return new CSVParser(new StringReader(tourismData), format);
    }
}
