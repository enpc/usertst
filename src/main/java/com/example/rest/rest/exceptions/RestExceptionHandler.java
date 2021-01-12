package com.example.rest.rest.exceptions;

import com.example.rest.services.users.exceptions.UserNotFoundException;
import com.example.rest.services.users.exceptions.UserParametersException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var message = ex.getBindingResult().getFieldErrors().stream()
                .map(s -> s.getField() + " : " +s.getDefaultMessage())
                .collect(Collectors.joining(System.lineSeparator()));
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({UserParametersException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<String> userParametersExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void>exceptionHandler(Exception ex){
        log.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
