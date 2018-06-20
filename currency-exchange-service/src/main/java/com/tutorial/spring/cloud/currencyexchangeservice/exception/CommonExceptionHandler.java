package com.tutorial.spring.cloud.currencyexchangeservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tutorial.spring.cloud.currencyexchangeservice.CurrencyNotFoundException;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CommonError> handleUserNotFoundException(final CurrencyNotFoundException exception) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CommonError error = new CommonError(HttpStatus.NOT_FOUND.toString(), new Date(), exception.getMessage());
        return new ResponseEntity<CommonError>(error, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CommonError> handleDefaultException(final Exception exception) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CommonError error = new CommonError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), new Date(), exception.getMessage());
        return new ResponseEntity<CommonError>(error, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        headers.setContentType(MediaType.APPLICATION_JSON);

        CommonError error = new CommonError(HttpStatus.BAD_REQUEST.toString(), new Date(), exception.getBindingResult().toString());
        return new ResponseEntity(error, headers, HttpStatus.BAD_REQUEST);
    }

}
