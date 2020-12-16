package com.example.rest.controllers;

import com.example.rest.services.UsersService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsersService usersService;

    @GetMapping()
    void login( @RequestBody @Valid LoginController.LoginRequest loginRequest){
        if (!usersService.userCanLogging(loginRequest.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    @Data
    static class LoginRequest {
        @NotBlank
        String name;
    }
}
