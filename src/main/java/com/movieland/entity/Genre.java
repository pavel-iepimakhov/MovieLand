package com.movieland.entity;

public class Genre {
    private  int genreId;
    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (getGenreId() != genre.getGenreId()) return false;
        return getGenreName().equals(genre.getGenreName());

    }

    @Override
    public int hashCode() {
        int result = getGenreId();
        result = 31 * result + getGenreName().hashCode();
        return result;
    }

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return getGenreName();
    }
}
