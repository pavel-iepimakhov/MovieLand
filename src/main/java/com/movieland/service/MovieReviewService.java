package com.movieland.service;

import com.movieland.entity.MovieReview;

import java.util.List;

public interface MovieReviewService {
    void addReview(int movieId, int userId, String reviewText);
    List<MovieReview> getReviewsByMovieId(int movieId);
}
