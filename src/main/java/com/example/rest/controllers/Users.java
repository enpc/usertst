package com.example.rest.controllers;

import com.example.rest.dao.Userr;
import com.example.rest.dao.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class Users {

    private UsersRepository repository;

    @Autowired
    public Users(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    private Userr getUser(@PathVariable("id") Long id){
        var user =  repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping
    private Iterable<Userr> getUsers(){
        return repository.findAll();
    }

    @PostMapping
    private String addUser(@RequestParam String name){
        var user = new Userr(name, true);
        repository.save(user);
        return "OK";
    }

}
