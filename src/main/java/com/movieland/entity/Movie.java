package com.movieland.entity;

import java.util.Arrays;

public class Movie {
    private int movieId;
    private String movieNameEng;
    private String movieNameRus;
    private int movieYear;
    private double movieRating;
    private double moviePrice;
    private String movieDescription;
    private String[] reviews;
    private String[] genres;

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

    public String[] getReviews() {
        return reviews;
    }

    public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
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
                ", reviews=" + Arrays.toString(reviews) +
                ", genres=" + Arrays.toString(genres) +
                '}';
    }
}
