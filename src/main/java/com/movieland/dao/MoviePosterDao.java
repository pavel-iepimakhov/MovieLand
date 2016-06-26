package com.movieland.dao;

import com.movieland.entity.Poster;

public interface MoviePosterDao {
    Poster getMoviePoster(int movieId);
}
