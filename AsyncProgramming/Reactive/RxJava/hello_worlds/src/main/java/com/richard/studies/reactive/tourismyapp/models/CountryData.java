package com.richard.studies.reactive.tourismyapp.models;

public class CountryData {
    private String geoArea;
    private String country;
    private Integer year;
    private Integer nights;

    public CountryData(String geoArea, String country, Integer year, Integer nights) {
        this.geoArea = geoArea;
        this.country = country;
        this.year = year;
        this.nights = nights;
    }

    public String getGeoArea() {
        return geoArea;
    }

    public String getCountry() {
        return country;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getNights() {
        return nights;
    }

    @Override
    public String toString() {
        return "CountryData{" +
                "geoArea='" + geoArea + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", nights=" + nights +
                '}';
    }
}
