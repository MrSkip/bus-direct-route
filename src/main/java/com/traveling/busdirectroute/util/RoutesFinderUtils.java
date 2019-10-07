package com.traveling.busdirectroute.util;

import com.traveling.busdirectroute.exception.FileNotFoundRuntimeException;
import com.traveling.busdirectroute.exception.GeneralServerRuntimeException;

import java.io.*;

public class RoutesFinderUtils {

    private RoutesFinderUtils() {
    }

    /**
     * Checking if there is a direct route between two stations
     *
     * @param fileDataLocation location of a file data on local storage
     * @param depSid           departure id of a station
     * @param arrSid           arrival id of a station
     * @return true if direct route exists otherwise false
     */
    public static boolean lookForDirectRoute(final String fileDataLocation, final int depSid, final int arrSid) {
        final String depStr = " " + depSid + " ";
        final String arrStr = " " + arrSid + " ";
        try (final BufferedReader reader = getBufferedStreamReader(fileDataLocation)) {
            String line;
            // first row omitted
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                line = line.trim() + " ";
                final int depIndex = line.indexOf(depStr);
                if (depIndex != -1) {
                    final int arrIndex = line.indexOf(arrStr, depIndex);
                    if (arrIndex > depIndex) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            throw new GeneralServerRuntimeException(e);
        }
        return false;
    }

    private static BufferedReader getBufferedStreamReader(String fileDataLocation) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(fileDataLocation)), 8_192);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundRuntimeException(String.format("File {%s} not found", fileDataLocation), e);
        }
    }

}
