package com.movieland.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.movieland.entity.Movie;
import com.movieland.entity.adapter.MovieTypeAdapter;
import com.movieland.service.JsonConverterService;
import org.springframework.stereotype.Service;

@Service
public class GsonJsonConverterService implements JsonConverterService {

    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;
    {
        gsonBuilder.registerTypeAdapter(Movie.class , new MovieTypeAdapter());
        gson = gsonBuilder.create();
    }


    @Override
    public String objectToJson(Object object) {
        return gson.toJson(object);
    }
}
