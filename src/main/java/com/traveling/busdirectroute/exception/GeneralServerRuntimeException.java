package com.traveling.busdirectroute.exception;

import org.springframework.http.HttpStatus;

public class GeneralServerRuntimeException extends RuntimeException {
    private static final String MESSAGE = "General Server Exception";

    public GeneralServerRuntimeException() {
        super(MESSAGE);
    }

    public GeneralServerRuntimeException(String message) {
        super(message);
    }

    public GeneralServerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralServerRuntimeException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public GeneralServerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
