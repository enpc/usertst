package com.example.rest.controllers;

import com.example.rest.services.UserDataRequest;
import com.example.rest.services.User;
import com.example.rest.services.UsersService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/admin")
public class UsersAdminController {

    @Autowired
    private UsersService usersService;

    @PostMapping(consumes = "application/json")
    public User createUser(@RequestBody @Valid UserDataRequest createUserRequest){
        return usersService.create(createUserRequest.getName());
    }

    @GetMapping
    public Iterable<User> getUsers(){
        return usersService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return usersService.getById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping(value = "/{id}", consumes = "application/json")
    public User updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDataRequest userDataRequest){
        return usersService.updateUserData(id, userDataRequest);
    }

    @PostMapping("/{id}/activate")
    public User activateUser(@PathVariable("id") Long id){
        return usersService.activateUser(id);
    }

    @PostMapping("/{id}/deactivate")
    public User deactivateUser(@PathVariable("id") Long id){
        return usersService.deactivateUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        usersService.deleteUser(id);
    }
}
