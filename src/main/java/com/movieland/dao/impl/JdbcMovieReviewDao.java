package com.movieland.dao.impl;

import com.movieland.dao.MovieReviewDao;
import com.movieland.dao.impl.mapper.MovieReviewRowMapper;
import com.movieland.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieReviewDao implements MovieReviewDao {

    @Autowired
    private String addMovieReviewSql;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getReviewsByMovieIdSql;

    @Autowired
    private String removeMovieReviewSql;

    private MovieReviewRowMapper movieReviewRowMapper = new MovieReviewRowMapper();


    @Override
    public void addReview(int movieId, int userId, String reviewText) {
        jdbcTemplate.update(addMovieReviewSql, movieId, userId, reviewText);
    }

    public List<MovieReview> getReviewsByMovieId(int movieId) {
        return jdbcTemplate.query(getReviewsByMovieIdSql, new Object[]{movieId}, movieReviewRowMapper);
    }

    @Override
    public void removeMovieReview(int reviewId) {
        jdbcTemplate.update(removeMovieReviewSql, reviewId);
    }

}
