package com.example.rest.rest.admin;

import com.example.rest.rest.admin.request.CreateUserRequest;
import com.example.rest.rest.admin.request.SetUserDataRequest;
import com.example.rest.rest.admin.response.UserDataResponse;
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
    UserDataResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest);

    @ApiOperation(value = "Retrieve all users")
    @ApiResponse(code = 200, message = "Return all users or empty list")
    @GetMapping
    List<UserDataResponse> getUsers();

    @ApiOperation(value = "Get user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @GetMapping("/{id}")
    UserDataResponse getUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Update user data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return updated user data"),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping(value = "/{id}", consumes = "application/json")
    UserDataResponse updateUser(@PathVariable("id") Long id, @RequestBody @Valid SetUserDataRequest setUserDataRequest);

    @ApiOperation(value = "Activate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User activated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/activate")
    UserDataResponse activateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Deactivate user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User disactivated."),
            @ApiResponse(code = 404, message = "Specified user id not found")
    })
    @PostMapping("/{id}/deactivate")
    UserDataResponse deactivateUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete user")
    @ApiResponse(code = 200, message = "User deleted.")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);
}
