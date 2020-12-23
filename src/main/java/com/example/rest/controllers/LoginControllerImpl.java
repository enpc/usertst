package com.example.rest.controllers;

import com.example.rest.controllers.dto.ChangePasswordRequestDto;
import com.example.rest.controllers.dto.LoginRequestDto;
import com.example.rest.services.dto.LoginRequest;
import com.example.rest.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private final UsersService usersService;

    @Override
    public void login(LoginRequestDto loginRequest){
        if (!usersService.login(loginRequest)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    @Override
    public void changePassword(ChangePasswordRequestDto changePasswordRequest){
        usersService.ChangePassword(changePasswordRequest);
    }
}
