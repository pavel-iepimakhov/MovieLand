package com.movieland.util.impl;

import com.google.common.cache.LoadingCache;
import com.movieland.cache.CacheService;
import com.movieland.util.CurrencyEnum;
import com.movieland.util.CurrencyExchangeRateService;
import com.movieland.util.ExchangeRate;
import com.movieland.util.JsonConverterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class NBUCurrencyExchangeRateService implements CurrencyExchangeRateService {

    @Value("${currency.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CacheService cacheService;

    @Override
    public ExchangeRate getCurrencyExchangeRate(CurrencyEnum currencyCode) {
        ExchangeRate exchangeRate = cacheService.getExchangeRateForCurrency(currencyCode);
        return exchangeRate;
    }


    public ExchangeRate fetchCurrencyExchangeRate(CurrencyEnum currencyCode) {
        ExchangeRate exchangeRate = null;
        String todayBasicISO = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String queryString = MessageFormat.format(url, currencyCode, todayBasicISO);
        String response = restTemplate.getForObject(queryString, String.class);
        ArrayList<ExchangeRate> parseResult = jsonConverterService.jsonToObject(response, ArrayList.class);
        if(parseResult != null && !parseResult.isEmpty()) {
            exchangeRate = modelMapper.map(parseResult.get(0), ExchangeRate.class);
        }
        return exchangeRate;
    }


}
