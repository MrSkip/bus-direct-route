package com.traveling.busdirectroute.service;

import com.traveling.busdirectroute.dto.BusDirectRouteDTO;

public interface BusRoutesService {

    BusDirectRouteDTO lookForDirectRoute(final int depSid, final int arrSid);

}
