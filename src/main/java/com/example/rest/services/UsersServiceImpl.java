package com.example.rest.services;

import com.example.rest.dao.UserDao;
import com.example.rest.dao.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDao create(String name) {

        if ((name == null) || (name.isEmpty())){
            throw new IllegalArgumentException("name can't be empty");
        }

        var user = new UserDao();
        user.setName(name);
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false)
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(Long id) {
        return  repository.findById(id).map(User.class::cast);
    }

}
