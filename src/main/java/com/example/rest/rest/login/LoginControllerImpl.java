package com.example.rest.rest.login;

import com.example.rest.services.users.ChangePasswordRequest;
import com.example.rest.services.users.LoginRequest;
import com.example.rest.services.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private final UsersService usersService;

    @Override
    public ResponseEntity<Void> login(LoginRequest loginRequest){
        if (!usersService.login(loginRequest)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> changePassword(ChangePasswordRequest changePasswordRequest){
        usersService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }
}
