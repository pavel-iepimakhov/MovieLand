package com.movieland.cache.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.movieland.entity.Genre;
import com.movieland.cache.CacheService;
import com.movieland.service.MovieService;
import com.movieland.util.CurrencyEnum;
import com.movieland.util.CurrencyExchangeRateService;
import com.movieland.util.ExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

// TODO: 16.06.2016 implement custom cache
@Service
public class GuavaCacheService implements CacheService {

    private final static Logger LOGGER = LoggerFactory.getLogger(GuavaCacheService.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private CurrencyExchangeRateService currencyExchangeRateService;

    private LoadingCache<Integer, List<Genre>> movieGenresCache =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(4, TimeUnit.HOURS)
                    .build(new CacheLoader<Integer, List<Genre>>() {
                        @Override
                        public List<Genre> load(Integer movieId) throws Exception {
                            return movieService.getGenresByMovieId(movieId);
                        }
                    });

    private LoadingCache<CurrencyEnum, ExchangeRate> currencyExchangeRateCache =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(10, TimeUnit.MINUTES)
                    .build(new CacheLoader<CurrencyEnum, ExchangeRate>() {
                        @Override
                        public ExchangeRate load(CurrencyEnum currencyEnum) throws Exception {
                            return currencyExchangeRateService.fetchCurrencyExchangeRate(currencyEnum);
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

    @Override
    public ExchangeRate getExchangeRateForCurrency(CurrencyEnum currency) {
        try{
            ExchangeRate rate = currencyExchangeRateCache.get(currency);
            return rate;
        } catch (ExecutionException e) {
            return null;
        }
    }

    @Override
    public void invalidateGenresCache() {
        LOGGER.info("Invalidating movie genres cache...");
        movieGenresCache.invalidateAll();
    }

    @Override
    public void invalidateExchangeRatesCache() {
        LOGGER.info("Invalidating currency exchange rates cache...");
        currencyExchangeRateCache.invalidateAll();
    }

}
