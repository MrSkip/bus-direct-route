package com.traveling.busdirectroute.service.impl;

import com.traveling.busdirectroute.dto.BusDirectRouteDTO;
import com.traveling.busdirectroute.exception.RequestParamsRuntimeException;
import com.traveling.busdirectroute.service.BusRoutesService;
import com.traveling.busdirectroute.util.RoutesFinderUtils;
import com.traveling.busdirectroute.util.SystemPropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BusRoutesServiceImpl implements BusRoutesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusRoutesServiceImpl.class);

    private final String fileDataLocationPropertyName;

    public BusRoutesServiceImpl(@Value("${app.file-data-location-property-name}") String fileDataLocationPropertyName) {
        this.fileDataLocationPropertyName = fileDataLocationPropertyName;
    }

    @Override
    public BusDirectRouteDTO lookForDirectRoute(int depSid, int arrSid) {
        LOGGER.info("Requesting the direct route for depSid = {} / arrSid = {}", depSid, arrSid);
        if (depSid == arrSid) {
            throw new RequestParamsRuntimeException("Request parameters could not be equal");
        }
        final String filePropertyLocation = SystemPropertiesUtils.obtain(fileDataLocationPropertyName);
        final boolean routeExists = RoutesFinderUtils.lookForDirectRoute(filePropertyLocation, depSid, arrSid);
        return new BusDirectRouteDTO(depSid, arrSid, routeExists);
    }

}
