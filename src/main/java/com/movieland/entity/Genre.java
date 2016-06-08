package com.movieland.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Genre {
    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    private class GenreArrayListSerializer implements JsonSerializer<ArrayList<Genre>> {
        public JsonElement serialize(ArrayList<Genre> src, Type typeOfSrc, JsonSerializationContext context) {

            src.
            return new JsonPrimitive(src.toString());
        }
    }
}
