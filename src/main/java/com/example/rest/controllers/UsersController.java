package com.example.rest.controllers;

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
@RequestMapping("/")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return usersService.getById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping
    public Iterable<User> getUsers(){
        return usersService.getAll();
    }

    @PostMapping(consumes = "application/json")
    public User createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        return usersService.create(createUserRequest.name);
    }

    @Data
    static class CreateUserRequest{
        @NotBlank
        private String name;
    }

}
