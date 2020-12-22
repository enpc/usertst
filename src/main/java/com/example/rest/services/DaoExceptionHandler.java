package com.example.rest.services;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@Aspect
public class DaoExceptionHandler {

    @AfterThrowing(
            pointcut = "execution(public com.example.rest.services.* *(..))",
            throwing = "e"
    )
    public void notFound(EntityNotFoundException e){
        throw new UserNotFoundException("user not found", e);
    }
}
