package com.example.rest.controllers;

import com.example.rest.services.User;
import com.example.rest.services.UserDataRequest;
import com.example.rest.services.UserNotFoundException;
import com.example.rest.services.UserParametersException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/admin")
public interface UsersAdminController {
    @ApiOperation(value = "Create new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User created"),
            @ApiResponse(code = 400, message = "User with this name already exists")
    })
    @PostMapping(consumes = "application/json")
    User createUser(@RequestBody @Valid UserDataRequest createUserRequest);

    @ApiOperation(value = "Retrieve all users")
    @ApiResponse(code = 200, message = "Return all users or empty list")
    @GetMapping
    Iterable<User> getUsers();

    @ApiOperation(value = "Get user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @GetMapping("/{id}")
    User getUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Update user data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return updated user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping(value = "/{id}", consumes = "application/json")
    User updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDataRequest userDataRequest);

    @ApiOperation(value = "Activate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User activated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/activate")
    User activateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Deactivate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User disactivated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/deactivate")
    User deactivateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete user")
    @ApiResponse(code = 200, message = "User deleted.")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
