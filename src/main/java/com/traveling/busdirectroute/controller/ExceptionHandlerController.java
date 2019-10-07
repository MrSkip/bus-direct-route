package com.traveling.busdirectroute.controller;

import com.traveling.busdirectroute.dto.ErrorResponseDTO;
import com.traveling.busdirectroute.exception.GeneralServerRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(GeneralServerRuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFound(final GeneralServerRuntimeException ex, final HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFound(final RuntimeException ex, final HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
