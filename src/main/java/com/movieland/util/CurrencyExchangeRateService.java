package com.movieland.util;

public interface CurrencyExchangeRateService {
    ExchangeRate getCurrencyExchangeRate(CurrencyEnum currencyCode);
    ExchangeRate fetchCurrencyExchangeRate(CurrencyEnum currencyCode);
}
