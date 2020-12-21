package com.example.rest.controllers;

import com.example.rest.controllers.dto.LoginRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface LoginController {
    @ApiOperation(value = "Login user by name", notes = "Return code 200 if user can be logged in")
    @GetMapping()
    void login(@RequestBody @Valid LoginRequest loginRequest);
}
