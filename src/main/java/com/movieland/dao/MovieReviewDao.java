package com.movieland.dao;

import com.movieland.entity.MovieReview;

import java.util.List;

public interface MovieReviewDao {
    void addReview(int movieId, int userId, String reviewText);
    List<MovieReview> getReviewsByMovieId(int movieId);
    void removeMovieReview(int reviewId);
    MovieReview getMovieReviewById(int reviewId);
}
