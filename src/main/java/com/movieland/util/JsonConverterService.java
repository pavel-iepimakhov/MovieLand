package com.movieland.util;


import java.util.Map;

public interface JsonConverterService {
    String objectToJson(Object object);
    <T> T jsonToObject(String json, Class<T> clazz);
    Map<String,String> getStringMapFromJson(String json);
}
