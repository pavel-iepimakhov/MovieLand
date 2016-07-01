package com.movieland.entity.request;

public class MergeMovieRatingRequest {
    private int movieId;
    private float rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MergeMovieRatingRequest{" +
                "movieId=" + movieId +
                ", rating=" + rating +
                '}';
    }
}
