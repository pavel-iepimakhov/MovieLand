package com.movieland.service;

public interface MovieRatingService {
    void mergeUserMovieRating(int movieId, int userId, float rating);
}
