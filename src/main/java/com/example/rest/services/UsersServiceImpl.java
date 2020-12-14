package com.example.rest.services;

import com.example.rest.dao.User;
import com.example.rest.dao.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public User create(String name) {
        var user = new User();
        user.setName(name);
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }
}
