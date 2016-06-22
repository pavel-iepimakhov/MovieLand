package com.movieland.service;

import com.movieland.entity.Genre;
import com.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(int movieId, Integer userId);
    List<Genre> getGenresByMovieId(int movieId);
}
