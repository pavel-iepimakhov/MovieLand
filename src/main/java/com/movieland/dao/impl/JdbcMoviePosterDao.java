package com.movieland.dao.impl;

import com.movieland.dao.MoviePosterDao;
import com.movieland.dao.impl.mapper.MoviePosterRowMapper;
import com.movieland.entity.Poster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMoviePosterDao implements MoviePosterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getMoviePosterSql;

    private MoviePosterRowMapper moviePosterRowMapper = new MoviePosterRowMapper();

    @Override
    public Poster getMoviePoster(int movieId) {
        Poster poster = jdbcTemplate.queryForObject(getMoviePosterSql, moviePosterRowMapper, movieId);
        return poster;
    }
}
