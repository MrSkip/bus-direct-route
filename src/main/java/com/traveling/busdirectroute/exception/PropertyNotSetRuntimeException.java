package com.traveling.busdirectroute.exception;

public class PropertyNotSetRuntimeException extends GeneralServerRuntimeException {

    public PropertyNotSetRuntimeException(String message) {
        super(message);
    }

    public PropertyNotSetRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
