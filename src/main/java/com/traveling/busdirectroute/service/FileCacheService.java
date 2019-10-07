package com.traveling.busdirectroute.service;

import java.util.List;
import java.util.Map;

public interface FileCacheService {

    Map<Integer, List<Integer>> loadBusRoutes();

}
