package com.example.rest.rest.login;

import com.example.rest.rest.login.request.ChangePasswordRequest;
import com.example.rest.rest.login.request.LoginRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping()
    ResponseEntity<Void> login(@RequestBody @Valid LoginRequest loginRequest);

    @ApiOperation(value = "Change user password", notes = "Change user password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User logged in"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "incorrect parameters"),
    })
    @PostMapping("/changepassword")
    ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest);
}
