package com.movieland.entity;

public class Poster {
    private int posterId;
    private int movieId;
    private byte[] posterImage;

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public byte[] getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(byte[] posterImage) {
        this.posterImage = posterImage;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "posterId=" + posterId +
                ", movieId=" + movieId +
                '}';
    }
}
