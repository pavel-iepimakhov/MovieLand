package com.movieland.dao.impl.mapper;

import com.movieland.entity.Country;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MoviesRowMapper implements RowMapper<Movie> {
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setMovieId(resultSet.getInt("movie_id"));
        movie.setMovieNameEng(resultSet.getString("movie_name_eng"));
        movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
        movie.setMovieYear(resultSet.getInt("movie_year"));
        movie.setMovieRating(resultSet.getDouble("movie_rating"));
        movie.setMoviePrice(resultSet.getDouble("movie_price"));

        ArrayList<Genre> genres = new ArrayList<>();
        for(String genre : resultSet.getString("genres").split(",")) {
            genres.add(new Genre(genre));
        }
        movie.setGenres(genres);

        ArrayList<Country> countries = new ArrayList<>();
        for(String country : resultSet.getString("countries").split(",")) {
            countries.add(new Country(country));
        }
        movie.setCountries(countries);

        return movie;
    }
}
