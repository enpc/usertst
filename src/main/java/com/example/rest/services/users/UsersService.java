package com.example.rest.services.users;

import java.util.Optional;

public interface UsersService {
    UserData create(CreateUserRequest request);

    Iterable<UserData> getAll();

    Optional<UserData> getById(Long id);

    Boolean login(LoginRequest loginRequest);

    UserData updateUserData(Long id, SetUserDataRequest request);

    UserData activateUser(Long id);

    UserData deactivateUser(Long id);

    UserData changePassword(ChangePasswordRequest changePasswordRequest);

    void deleteUser(Long id);
}
