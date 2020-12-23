package com.example.rest.controllers;

import com.example.rest.controllers.dto.CreateUserRequestDto;
import com.example.rest.controllers.dto.UserSetPropertiesRequestDto;
import com.example.rest.services.*;
import com.example.rest.services.dto.CreateUserRequest;
import com.example.rest.services.dto.UserProperties;
import com.example.rest.services.dto.UserSetPropertiesRequest;
import com.example.rest.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsersAdminControllerImpl implements UsersAdminController {

    private final UsersService usersService;

    @Override
    public UserProperties createUser(CreateUserRequestDto createUserRequest) {
        return usersService.create(createUserRequest);
    }

    @Override
    public Iterable<UserProperties> getUsers() {
        return usersService.getAll();
    }

    @Override
    public UserProperties getUser(Long id) {
        return usersService.getById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserProperties updateUser(Long id, UserSetPropertiesRequestDto userSetPropertiesRequest) {
        return usersService.updateUserData(id, userSetPropertiesRequest);
    }

    @Override
    public UserProperties activateUser(Long id) {
        return usersService.activateUser(id);
    }

    @Override
    public UserProperties deactivateUser(Long id) {
        return usersService.deactivateUser(id);
    }

    @Override
    public void deleteUser(Long id) {
        usersService.deleteUser(id);
    }
}
