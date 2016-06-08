package com.movieland.entity;

public class Country {
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return countryName;
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }
}
