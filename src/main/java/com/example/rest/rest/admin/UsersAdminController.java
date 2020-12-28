package com.example.rest.rest.admin;

import com.example.rest.services.users.CreateUserRequest;
import com.example.rest.services.users.SetUserDataRequest;
import com.example.rest.services.users.UserData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin")
public interface UsersAdminController {
    @ApiOperation(value = "Create new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User created"),
            @ApiResponse(code = 400, message = "User with this name already exists")
    })
    @PostMapping(consumes = "application/json")
    UserData createUser(@RequestBody @Valid CreateUserRequest createUserRequest);

    @ApiOperation(value = "Retrieve all users")
    @ApiResponse(code = 200, message = "Return all users or empty list")
    @GetMapping
    List<UserData> getUsers();

    @ApiOperation(value = "Get user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @GetMapping("/{id}")
    UserData getUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Update user data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return updated user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping(value = "/{id}", consumes = "application/json")
    UserData updateUser(@PathVariable("id") Long id, @RequestBody @Valid SetUserDataRequest setUserDataRequest);

    @ApiOperation(value = "Activate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User activated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/activate")
    UserData activateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Deactivate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User disactivated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/deactivate")
    UserData deactivateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete user")
    @ApiResponse(code = 200, message = "User deleted.")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);
}
