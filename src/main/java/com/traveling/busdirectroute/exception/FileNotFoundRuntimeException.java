package com.traveling.busdirectroute.exception;

public class FileNotFoundRuntimeException extends GeneralServerRuntimeException {

    public FileNotFoundRuntimeException(String message) {
        super(message);
    }

    public FileNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
