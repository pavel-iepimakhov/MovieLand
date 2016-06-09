package com.movieland.entity.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import org.springframework.util.StringUtils;

import java.io.IOException;


public class MovieTypeAdapter extends TypeAdapter<Movie> {

    @Override
    public Movie read(JsonReader jsonReader) throws IOException {
        return null;
    }

    @Override
    public void write(JsonWriter jsonWriter,Movie movie) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("title").value(movie.getMovieNameRus() + "/" + movie.getMovieNameEng());
        if(movie.getMovieYear() != 0) jsonWriter.name("year").value(movie.getMovieYear());
        if(movie.getMovieDescription() != null) jsonWriter.name("description").value(movie.getMovieDescription());
        if(movie.getMovieRating() != 0) jsonWriter.name("rating").value(movie.getMovieRating());
        if(movie.getMoviePrice() != 0) jsonWriter.name("price").value(movie.getMoviePrice());
        if(movie.getGenres() != null) jsonWriter.name("genres").value(StringUtils.arrayToCommaDelimitedString(movie.getGenres().toArray()));
        if(movie.getCountries() != null) jsonWriter.name("countries").value(StringUtils.arrayToCommaDelimitedString(movie.getCountries().toArray()));
        if(movie.getReviews() != null) jsonWriter.name("reviews").value(StringUtils.arrayToCommaDelimitedString(movie.getReviews().toArray()));
        jsonWriter.endObject();
    }
}
