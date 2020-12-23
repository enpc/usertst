package com.example.rest.controllers;

import com.example.rest.controllers.dto.CreateUserRequestDto;
import com.example.rest.controllers.dto.UserSetPropertiesRequestDto;
import com.example.rest.services.dto.CreateUserRequest;
import com.example.rest.services.dto.UserProperties;
import com.example.rest.services.dto.UserSetPropertiesRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.CacheRequest;

@RequestMapping("/admin")
public interface UsersAdminController {
    @ApiOperation(value = "Create new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User created"),
            @ApiResponse(code = 400, message = "User with this name already exists")
    })
    @PostMapping(consumes = "application/json")
    UserProperties createUser(@RequestBody @Valid CreateUserRequestDto createUserRequest);

    @ApiOperation(value = "Retrieve all users")
    @ApiResponse(code = 200, message = "Return all users or empty list")
    @GetMapping
    Iterable<UserProperties> getUsers();

    @ApiOperation(value = "Get user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @GetMapping("/{id}")
    UserProperties getUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Update user data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return updated user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping(value = "/{id}", consumes = "application/json")
    UserProperties updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserSetPropertiesRequestDto userSetPropertiesRequest);

    @ApiOperation(value = "Activate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User activated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/activate")
    UserProperties activateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Deactivate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User disactivated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/deactivate")
    UserProperties deactivateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete user")
    @ApiResponse(code = 200, message = "User deleted.")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
