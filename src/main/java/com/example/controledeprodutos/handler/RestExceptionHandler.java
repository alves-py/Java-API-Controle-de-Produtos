package com.example.controledeprodutos.handler;

import com.example.controledeprodutos.model.error.ErrorMessage;
import com.example.controledeprodutos.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage err = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage() );
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
