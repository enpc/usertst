package com.example.rest.services;

import java.util.Optional;

public interface UsersService {
    User create(String name);

    Iterable<User> getAll();
    Optional<User> getById(Long id);
}
