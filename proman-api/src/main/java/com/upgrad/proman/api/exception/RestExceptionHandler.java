package com.upgrad.proman.api.exception;

import com.upgrad.proman.api.model.ErrorResponse;
import com.upgrad.proman.service.exception.AuthenticationFailedException;
import com.upgrad.proman.service.exception.ResourceNotFoundException;
import com.upgrad.proman.service.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException exception, WebRequest web){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exception.getCode())
                .message(exception.getErrorMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> authenticationFailedException(AuthenticationFailedException exception, WebRequest web){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse()
                .code(exception.getCode())
                .message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorizedException(UnauthorizedException exception, WebRequest web){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse()
                .code(exception.getCode())
                .message(exception.getErrorMessage()),
                HttpStatus.UNAUTHORIZED);
    }
}
