package com.movieland.service.impl;

import com.movieland.dao.MovieDao;
import com.movieland.entity.Movie;
import com.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

}
