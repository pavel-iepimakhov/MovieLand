package com.movieland.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.movieland.dao.MovieDao;
import com.movieland.entity.Genre;
import com.movieland.service.MovieGenresCacheService;
import com.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class GuavaMovieGenresCacheService implements MovieGenresCacheService {

    @Autowired
    private MovieService movieService;

    private LoadingCache<Integer, List<Genre>> movieGenresCache =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(4, TimeUnit.HOURS)
                    .build(new CacheLoader<Integer, List<Genre>>() {
                        @Override
                        public List<Genre> load(Integer movieId) throws Exception {
                            return movieService.getGenresByMovieId(movieId);
                        }
                    });

    @Override
    public List<Genre> getMovieGenres(int movieId) {
        try {
            return movieGenresCache.get(movieId);
        } catch (ExecutionException e) {
            return movieService.getGenresByMovieId(movieId);
        }
    }
}
