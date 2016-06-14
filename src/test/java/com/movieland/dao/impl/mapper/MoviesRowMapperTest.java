package com.movieland.dao.impl.mapper;

import com.movieland.entity.Country;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MoviesRowMapperTest {
    @Test
    public void testMapRow() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("movie_id")).thenReturn(111);
        when(resultSet.getString("movie_name_eng")).thenReturn("English title");
        when(resultSet.getString("movie_name_rus")).thenReturn("Русское название");
        when(resultSet.getInt("movie_year")).thenReturn(2016);
        when(resultSet.getDouble("movie_rating")).thenReturn(10.0);
        when(resultSet.getString("genres")).thenReturn("драма,комедия,боевик");
        when(resultSet.getString("countries")).thenReturn("США,Германия");

        MoviesRowMapper moviesRowMapper = new MoviesRowMapper();
        Movie actualMovie = moviesRowMapper.mapRow(resultSet, 0);

        assertTrue(actualMovie.getMovieId() == 111);
        assertTrue(actualMovie.getMovieNameEng().equals("English title"));
        assertTrue(actualMovie.getMovieNameRus().equals("Русское название"));
        assertTrue(actualMovie.getMovieYear() == 2016);
        assertTrue(actualMovie.getMovieRating() == 10.0);
        Genre[] genresCheckArray = {new Genre("драма"), new Genre("комедия"), new Genre("боевик")};
        assertTrue(Arrays.equals(actualMovie.getGenres().toArray(), genresCheckArray));
        Country[] countriesCheckArray = {new Country("США"), new Country("Германия")};
        assertTrue(Arrays.equals(actualMovie.getCountries().toArray(), countriesCheckArray));
    }


}