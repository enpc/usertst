package com.example.rest.services.users;

import com.example.rest.rest.admin.request.CreateUserRequest;
import com.example.rest.rest.admin.request.SetUserDataRequest;
import com.example.rest.rest.admin.response.UserDataResponse;
import com.example.rest.rest.login.request.ChangePasswordRequest;
import com.example.rest.rest.login.request.LoginRequest;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    UserDataResponse create(CreateUserRequest request);

    List<UserDataResponse> getAll();

    Optional<UserDataResponse> getById(Long id);
    Optional<UserDataResponse> getByName(String name);

    Boolean login(LoginRequest loginRequest);

    UserDataResponse updateUserData(Long id, SetUserDataRequest request);

    UserDataResponse activateUser(Long id);

    UserDataResponse deactivateUser(Long id);

    UserDataResponse changePassword(ChangePasswordRequest changePasswordRequest);

    void deleteUser(Long id);
}
