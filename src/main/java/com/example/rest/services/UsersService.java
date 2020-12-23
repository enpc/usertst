package com.example.rest.services;

import com.example.rest.services.dto.*;

import java.util.Optional;

public interface UsersService {
    UserProperties create(CreateUserRequest request);

    Iterable<UserProperties> getAll();
    Optional<UserProperties> getById(Long id);
    Boolean login(LoginRequest loginRequest);

    UserProperties updateUserData(Long id, UserSetPropertiesRequest request);
    UserProperties activateUser(Long id);
    UserProperties deactivateUser(Long id);
    UserProperties ChangePassword(ChangePasswordRequest changePasswordRequest);

    void deleteUser(Long id);
}
