package com.movieland.cache;

import com.movieland.entity.Genre;
import com.movieland.util.CurrencyEnum;
import com.movieland.util.ExchangeRate;

import java.util.List;

public interface CacheService {
    List<Genre> getMovieGenres(int movieId);
    ExchangeRate getExchangeRateForCurrency(CurrencyEnum currency);
}
