package com.example.rest.services.users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    UserDataResponse create(CreateUserRequest request);

    List<UserDataResponse> getAll();

    Optional<UserDataResponse> getById(Long id);

    Boolean login(LoginRequest loginRequest);

    UserDataResponse updateUserData(Long id, SetUserDataRequest request);

    UserDataResponse activateUser(Long id);

    UserDataResponse deactivateUser(Long id);

    UserDataResponse changePassword(ChangePasswordRequest changePasswordRequest);

    void deleteUser(Long id);
}
