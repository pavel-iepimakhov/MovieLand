package com.movieland.entity.request;

public class RemoveMovieReviewRequest {
    private int reviewId;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        return "RemoveMovieReviewRequest{" +
                "reviewId=" + reviewId +
                '}';
    }
}
