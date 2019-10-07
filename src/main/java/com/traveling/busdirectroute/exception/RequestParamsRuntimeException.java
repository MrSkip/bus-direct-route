package com.traveling.busdirectroute.exception;

import org.springframework.http.HttpStatus;

public class RequestParamsRuntimeException extends GeneralServerRuntimeException {

    public RequestParamsRuntimeException(String message) {
        super(message);
    }

    public RequestParamsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
