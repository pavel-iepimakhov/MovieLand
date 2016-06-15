package com.movieland.service;


import java.util.Map;

public interface JsonConverterService {
    String objectToJson(Object object);
    Map<String,String> getStringMapFromJson(String json);
}
