package com.movieland.dao.impl.mapper;

import com.movieland.entity.Poster;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoviePosterRowMapper implements RowMapper<Poster> {
    @Override
    public Poster mapRow(ResultSet resultSet, int i) throws SQLException {
        Poster poster = new Poster();
        poster.setMovieId(resultSet.getInt("movie_id"));
        poster.setPosterId(resultSet.getInt("poster_id"));
        poster.setPosterImage(resultSet.getBytes("poster_image"));
        return poster;
    }
}
