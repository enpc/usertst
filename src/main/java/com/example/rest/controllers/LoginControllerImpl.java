package com.example.rest.controllers;

import com.example.rest.controllers.dto.LoginRequest;
import com.example.rest.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private UsersService usersService;

    @Override
    public void login(LoginRequest loginRequest){
        if (!usersService.userCanLogging(loginRequest.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }
}
