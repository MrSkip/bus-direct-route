package com.traveling.busdirectroute.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.traveling.busdirectroute.config.constants.CacheNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@EnableCaching
public class CaffeineConfig {

    @Autowired
    public CaffeineConfig() {
    }

    @Primary
    @Bean(name = "primaryCache")
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(
                buildCache(CacheNameConstants.BUS_ROUTES_CACHE)
        ));
        return manager;
    }

    private CaffeineCache buildCache(String name) {
        return new CaffeineCache(name, Caffeine.newBuilder().build());
    }

}