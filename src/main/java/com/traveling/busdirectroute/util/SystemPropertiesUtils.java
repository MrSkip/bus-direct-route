package com.traveling.busdirectroute.util;

import com.traveling.busdirectroute.exception.PropertyNotSetRuntimeException;

public class SystemPropertiesUtils {

    private SystemPropertiesUtils() {
    }

    public static String obtain(final String propertyName) {
        final String value = System.getProperty(propertyName);
        if (value == null || value.trim().isEmpty()) {
            throw new PropertyNotSetRuntimeException(String.format("Missing property {%s}", propertyName));
        }
        return value;
    }

}
