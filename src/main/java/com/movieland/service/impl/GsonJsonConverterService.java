package com.movieland.service.impl;

import com.google.gson.Gson;
import com.movieland.service.JsonConverterService;
import org.springframework.stereotype.Service;

@Service
public class GsonJsonConverterService implements JsonConverterService {

    private Gson gson = new Gson();

    @Override
    public String objectToJson(Object object) {
        String json = gson.toJson(object);
        return json;
    }
}
