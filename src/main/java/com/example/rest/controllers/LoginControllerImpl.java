package com.example.rest.controllers;

import com.example.rest.controllers.dto.LoginRequest;
import com.example.rest.services.UsersService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
public class LoginControllerImpl implements LoginController {

    @Autowired
    private UsersService usersService;

    @Override
    @ApiOperation(value = "Login user by name", notes = "Return code 200 if user can be logged in")
    @GetMapping()
    public void login(@RequestBody @Valid LoginRequest loginRequest){
        if (!usersService.userCanLogging(loginRequest.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }
}
