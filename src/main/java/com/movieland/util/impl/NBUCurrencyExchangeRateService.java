package com.movieland.util.impl;

import com.movieland.util.CurrencyExchangeRateService;
import com.movieland.util.ExchangeRate;
import com.movieland.util.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class NBUCurrencyExchangeRateService implements CurrencyExchangeRateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JsonConverterService jsonConverterService;

    @Override
    public ExchangeRate getCurrencyExchangeRate(String currencyCode) {
        LocalDate today = LocalDate.now();
        String queryString = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode="
                + currencyCode + "&date=" + today.format(DateTimeFormatter.BASIC_ISO_DATE) + "&json";
        String response = restTemplate.getForObject(queryString, String.class);
        ArrayList parseResult = (ArrayList) jsonConverterService.jsonToObject(response);
        return null;
    }
}
