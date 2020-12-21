package com.example.rest.controllers;

import com.example.rest.services.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersAdminControllerImpl implements UsersAdminController {

    private final UsersService usersService;

    @Override
    public User createUser(UserDataRequest createUserRequest) {
        return usersService.create(createUserRequest.getName());
    }

    @Override
    public Iterable<User> getUsers() {
        return usersService.getAll();
    }

    @Override
    public User getUser(@PathVariable("id") Long id) {
        return usersService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public User updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDataRequest userDataRequest) {
        return usersService.updateUserData(id, userDataRequest);
    }

    @Override
    public User activateUser(@PathVariable("id") Long id) {
        return usersService.activateUser(id);
    }

    @Override
    public User deactivateUser(@PathVariable("id") Long id) {
        return usersService.deactivateUser(id);
    }

    @Override
    public void deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUser(id);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFoundExceptionHandler() {

    }

    @ExceptionHandler(UserParametersException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void userParametersExceptionHandler() {
    }
}
