package com.example.rest.controllers;

import com.example.rest.services.UsersService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Login user by name", notes = "Return code 200 if user can be logged in")
    @GetMapping()
    void login( @RequestBody @Valid LoginController.LoginRequest loginRequest){
        if (!usersService.userCanLogging(loginRequest.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    @Data
    static class LoginRequest {
        @ApiModelProperty(value = "User name",example = "User")
        @NotBlank
        String name;
    }
}
