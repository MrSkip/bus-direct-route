package com.traveling.busdirectroute.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusDirectRouteDTO {

    @JsonProperty("dep_sid")
    private int depSid;
    @JsonProperty("arr_sid")
    private int arrSid;
    @JsonProperty("direct_bus_route")
    private boolean directBusRoute;

}
