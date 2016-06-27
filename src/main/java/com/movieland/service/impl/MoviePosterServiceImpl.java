package com.movieland.service.impl;

import com.movieland.dao.MoviePosterDao;
import com.movieland.entity.Poster;
import com.movieland.service.MoviePosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviePosterServiceImpl implements MoviePosterService {

    @Autowired
    private MoviePosterDao moviePosterDao;

    @Override
    public Poster getMoviePoster(int movieId) {
        return moviePosterDao.getMoviePoster(movieId);
    }
}
