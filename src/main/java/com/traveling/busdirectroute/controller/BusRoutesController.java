package com.traveling.busdirectroute.controller;

import com.traveling.busdirectroute.dto.BusDirectRouteDTO;
import com.traveling.busdirectroute.service.BusRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BusRoutesController {

    private final BusRoutesService busRoutesService;

    @Autowired
    public BusRoutesController(BusRoutesService busRoutesService) {
        this.busRoutesService = busRoutesService;
    }

    @GetMapping("/direct")
    public BusDirectRouteDTO lookForDirectRoute(@RequestParam("dep_sid") final int depSid,
                                                @RequestParam("arr_sid") final int arrSid) {
        return this.busRoutesService.lookForDirectRoute(depSid, arrSid);
    }

}
