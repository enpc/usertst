package com.example.rest.rest.exceptions;

import com.example.rest.services.users.exceptions.UserNotFoundException;
import com.example.rest.services.users.exceptions.UserParametersException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var message = ex.getBindingResult().getFieldErrors().stream()
                .map(s -> new Object(){
                    public String field = s.getField();
                    public String message = s.getDefaultMessage();
                })
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorMessage(message));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(UserNotFoundException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserParametersException.class)
    public ResponseEntity<Object> userParametersExceptionHandler(UserParametersException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object>exceptionHandler(Exception ex){
        logger.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
