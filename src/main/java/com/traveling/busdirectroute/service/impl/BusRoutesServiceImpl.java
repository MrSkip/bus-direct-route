package com.traveling.busdirectroute.service.impl;

import com.traveling.busdirectroute.dto.BusDirectRouteDTO;
import com.traveling.busdirectroute.exception.RequestParamsRuntimeException;
import com.traveling.busdirectroute.service.BusRoutesService;
import com.traveling.busdirectroute.service.FileCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusRoutesServiceImpl implements BusRoutesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusRoutesServiceImpl.class);

    private final FileCacheService fileCacheService;

    public BusRoutesServiceImpl(@Autowired FileCacheService fileCacheService) {
        this.fileCacheService = fileCacheService;
    }

    /**
     * Checking if there is a direct route between two stations
     *
     * @param depSid departure id of a station
     * @param arrSid arrival id of a station
     * @return true if direct route exists otherwise false
     */
    @Override
    public BusDirectRouteDTO lookForDirectRoute(int depSid, int arrSid) {
        LOGGER.info("Requesting the direct route for depSid = {} / arrSid = {}", depSid, arrSid);
        if (depSid == arrSid) {
            throw new RequestParamsRuntimeException("Request parameters could not be equal");
        }
        final boolean routeExists = fileCacheService.loadBusRoutes().values()
                .stream().anyMatch(stations -> stations.contains(depSid) && stations.contains(arrSid));
        return new BusDirectRouteDTO(depSid, arrSid, routeExists);
    }

}
