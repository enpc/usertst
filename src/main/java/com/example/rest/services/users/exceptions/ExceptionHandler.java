package com.example.rest.services.users.exceptions;

import com.example.rest.services.users.exceptions.UserNotFoundException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@Aspect
public class ExceptionHandler {

    @AfterThrowing(
            pointcut = "execution(public com.example.rest.services.* *(..))",
            throwing = "e"
    )
    public void notFound(EntityNotFoundException e){
        throw new UserNotFoundException("user not found", e);
    }
}
