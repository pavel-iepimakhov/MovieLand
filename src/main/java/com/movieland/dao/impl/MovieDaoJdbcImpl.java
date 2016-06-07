package com.movieland.dao.impl;

import com.movieland.dao.MovieDao;
import com.movieland.dao.impl.mapper.MovieRowMapper;
import com.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDaoJdbcImpl implements MovieDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Movie> getAllMovies() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m.movie_id, m.movie_name_eng, m.movie_name_rus, m.movie_year, group_concat(g.genre_name,', ') genres ");
        sb.append("FROM movies m ");
        sb.append("JOIN movie_genres mg ");
        sb.append("ON m.movie_id = mg.movie_id ");
        sb.append("JOIN genres g ");
        sb.append("ON mg.genre_id = g.genre_id ");
        sb.append("GROUP BY m.movie_id,m.movie_name_eng,m.movie_name_rus,m.movie_countries,m.movie_year,m.movie_price");
        return this.jdbcTemplate.query(sb.toString(), new MovieRowMapper());
    }
}
