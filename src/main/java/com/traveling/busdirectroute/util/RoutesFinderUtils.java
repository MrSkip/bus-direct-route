package com.traveling.busdirectroute.util;

import com.traveling.busdirectroute.exception.FileNotFoundRuntimeException;
import com.traveling.busdirectroute.exception.GeneralServerRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoutesFinderUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutesFinderUtils.class);

    private RoutesFinderUtils() {
    }

    private static BufferedReader getBufferedStreamReader(String fileDataLocation) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(fileDataLocation)), 8_192);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundRuntimeException(String.format("File {%s} not found", fileDataLocation), e);
        }
    }

    public static Map<Integer, List<Integer>> loadBusRoutes(final String fileDataLocation) {
        LOGGER.info("Started loading file data");
        try (final BufferedReader reader = getBufferedStreamReader(fileDataLocation)) {
            final String firstLine = reader.readLine().trim();
            final Map<Integer, List<Integer>> map = new HashMap<>(Integer.parseInt(firstLine));
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> list = null;
                for (String s : line.trim().split(" ")) {
                    final int station = Integer.parseInt(s);
                    if (list == null) {
                        list = new ArrayList<>();
                        map.put(station, list);
                    } else {
                        list.add(station);
                    }
                }
            }
            LOGGER.info("Finished loading file data");
            return map;
        } catch (IOException e) {
            throw new GeneralServerRuntimeException(e);
        }
    }

}
