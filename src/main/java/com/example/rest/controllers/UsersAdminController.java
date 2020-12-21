package com.example.rest.controllers;

import com.example.rest.services.User;
import com.example.rest.services.UserDataRequest;
import com.example.rest.services.UserNotFoundException;
import com.example.rest.services.UserParametersException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/admin")
public interface UsersAdminController {
    @ApiOperation(value = "Create new user")
    @PostMapping(consumes = "application/json")
    User createUser(@RequestBody @Valid UserDataRequest createUserRequest);

    @ApiOperation(value = "Retrieve all users")
    @GetMapping
    Iterable<User> getUsers();

    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    User getUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Update user data")
    @PostMapping(value = "/{id}", consumes = "application/json")
    User updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDataRequest userDataRequest);

    @ApiOperation(value = "Activate user")
    @PostMapping("/{id}/activate")
    User activateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Deactivate user")
    @PostMapping("/{id}/deactivate")
    User deactivateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
