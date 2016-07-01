package com.movieland.cache.impl;

import com.movieland.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@ManagedResource(objectName = "MovieLand:name=CacheInvalidator")
public class CacheJMXControl {

    @Autowired
    private CacheService cacheService;

    @ManagedOperation(description = "Invalidate genres cache")
    public void invalidateGenresCache() {
        cacheService.invalidateGenresCache();
    }

    @ManagedOperation(description = "Invalidate currency exchange rates cache")
    public void invalidateExchangeRatesCache() {
        cacheService.invalidateExchangeRatesCache();
    }

}
