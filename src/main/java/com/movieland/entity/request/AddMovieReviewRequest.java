package com.movieland.entity.request;

public class AddMovieReviewRequest {
    private int movieId;
    private String reviewText;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "AddMovieReviewRequest{" +
                "movieId=" + movieId +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
