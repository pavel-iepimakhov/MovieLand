package com.movieland.dao.impl;

import com.movieland.dao.MovieRatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieRatingDao implements MovieRatingDao {

    @Autowired
    private String mergeUserMovieRatingSql;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void mergeUserMovieRating(int movieId, int userId, float rating) {
        jdbcTemplate.update(mergeUserMovieRatingSql, movieId, userId, rating);
    }
}
