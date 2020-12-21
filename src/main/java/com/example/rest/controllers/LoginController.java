package com.example.rest.controllers;

import com.example.rest.controllers.dto.LoginRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/login")
public interface LoginController {
    @ApiOperation(value = "Login user by name", notes = "Return code 200 if user can be logged in")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User logged in"),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    @GetMapping()
    void login(@RequestBody @Valid LoginRequest loginRequest);
}
