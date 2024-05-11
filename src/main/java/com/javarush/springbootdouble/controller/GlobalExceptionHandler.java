package com.javarush.springbootdouble.controller;

import com.javarush.springbootdouble.service.exception.EntityNotFoundRuntimeException;
import com.javarush.springbootdouble.service.exception.EntityType;
import com.javarush.springbootdouble.service.exception.ExceptionResponse;
import com.javarush.springbootdouble.service.exception.RegistrationRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundRuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundRuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage(), e.getEntityType()));
    }

    @ExceptionHandler(RegistrationRuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRegistrationException(RegistrationRuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage(), EntityType.USER));
    }
}
