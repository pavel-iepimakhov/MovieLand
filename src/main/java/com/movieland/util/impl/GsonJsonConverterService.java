package com.movieland.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.movieland.entity.Movie;
import com.movieland.entity.adapter.MovieTypeAdapter;
import com.movieland.util.JsonConverterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GsonJsonConverterService implements JsonConverterService {

    private Gson gson;
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Movie.class , new MovieTypeAdapter());
        gson = gsonBuilder.create();
    }


    @Override
    public String objectToJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    @Override
    public Map<String, String> getStringMapFromJson(String json) {
        return gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
    }


}
