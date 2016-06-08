package com.movieland.entity;

import java.util.ArrayList;

public class Movie {
    private int movieId;
    private String movieNameEng;
    private String movieNameRus;
    private int movieYear;
    private double movieRating;
    private double moviePrice;
    private String movieDescription;
    private ArrayList<MovieReview> reviews;
    private ArrayList<Genre> genres;
    private ArrayList<Country> countries;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieNameEng() {
        return movieNameEng;
    }

    public void setMovieNameEng(String movieNameEng) {
        this.movieNameEng = movieNameEng;
    }

    public String getMovieNameRus() {
        return movieNameRus;
    }

    public void setMovieNameRus(String movieNameRus) {
        this.movieNameRus = movieNameRus;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public double getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(double moviePrice) {
        this.moviePrice = moviePrice;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public ArrayList<MovieReview> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<MovieReview> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieNameEng='" + movieNameEng + '\'' +
                ", movieNameRus='" + movieNameRus + '\'' +
                ", movieYear=" + movieYear +
                ", movieRating=" + movieRating +
                ", moviePrice=" + moviePrice +
                ", movieDescription='" + movieDescription + '\'' +
                ", reviews=" + reviews +
                ", genres=" + genres +
                ", countries=" + countries +
                '}';
    }
}
