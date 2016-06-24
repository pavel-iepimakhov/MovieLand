package com.movieland.dao;

public interface MovieRatingDao {
    void mergeUserMovieRating(int movieId, int userId, float rating);
    Float getUserMovieRating(int movieId, int userId);
}
