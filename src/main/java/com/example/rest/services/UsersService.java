package com.example.rest.services;

import java.util.Optional;

public interface UsersService {
    User create(String name);

    Iterable<User> getAll();
    Optional<User> getById(Long id);
    Boolean userCanLogging(String name);

    User updateUserData(Long id, UserDataRequest request);
    User activateUser(Long id);
    User deactivateUser(Long id);

    void deleteUser(Long user);
}
