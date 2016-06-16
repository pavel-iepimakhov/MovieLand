package com.movieland.cache;

import com.movieland.entity.Genre;

import java.util.List;

public interface MovieGenresCacheService {
    List<Genre> getMovieGenres(int movieId);
}
