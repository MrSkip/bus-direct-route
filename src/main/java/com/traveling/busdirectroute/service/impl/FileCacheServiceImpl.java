package com.traveling.busdirectroute.service.impl;

import com.traveling.busdirectroute.config.constants.CacheNameConstants;
import com.traveling.busdirectroute.service.FileCacheService;
import com.traveling.busdirectroute.util.RoutesFinderUtils;
import com.traveling.busdirectroute.util.SystemPropertiesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FileCacheServiceImpl implements FileCacheService {
    private final String fileDataLocationPropertyName;

    public FileCacheServiceImpl(@Value("${app.file-data-location-property-name}") String fileDataLocationPropertyName) {
        this.fileDataLocationPropertyName = fileDataLocationPropertyName;
    }

    @Override
    @Cacheable(value = CacheNameConstants.BUS_ROUTES_CACHE)
    public Map<Integer, List<Integer>> loadBusRoutes() {
        final String path = SystemPropertiesUtils.obtain(fileDataLocationPropertyName);
        return RoutesFinderUtils.loadBusRoutes(path);
    }

}
