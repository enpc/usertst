package com.example.rest.services.users.impl;

import com.example.rest.entityes.UserEntity;
import com.example.rest.repositoryes.UserRepository;
import com.example.rest.rest.admin.request.CreateUserRequest;
import com.example.rest.rest.admin.request.SetUserDataRequest;
import com.example.rest.rest.admin.response.UserDataResponse;
import com.example.rest.rest.login.request.ChangePasswordRequest;
import com.example.rest.rest.login.request.LoginRequest;
import com.example.rest.services.users.*;
import com.example.rest.exceptions.UserNotFoundException;
import com.example.rest.exceptions.UserParametersException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository repository;

    private final UserDataMapper modelMapper;

    @Override
    public UserDataResponse create(CreateUserRequest request) {
        try {
            var user = UserEntity.builder()
                    .name(request.getName())
                    .active(true)
                    .password(encodePassword(request.getPassword()))
                    .build();
            return modelMapper.userEntityToUserData(repository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new UserParametersException("user with this name already exists");
        }
    }

    @Override
    public List<UserDataResponse> getAll() {
        return repository.findAll().stream()
                .map(modelMapper::userEntityToUserData)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDataResponse> getById(Long id) {
        return repository.findById(id).map(modelMapper::userEntityToUserData);
    }

    @Override
    public Optional<UserDataResponse> getByName(String name) {
        return repository.findByName(name).map(modelMapper::userEntityToUserData);
    }

    @Override
    public Boolean login(LoginRequest loginRequest) {
        return repository.findByName(loginRequest.getName())
                .map(v -> v.getActive() && v.getPassword().equals(encodePassword(loginRequest.getPassword())))
                .orElse(false);
    }

    @Override
    public UserDataResponse updateUserData(Long id, SetUserDataRequest request) {
        return changeUser(
                repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")),
                usr -> usr
                        .name(request.getName())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName()));
    }

    @Override
    public UserDataResponse activateUser(Long id) {
        return changeUser(
                repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")),
                usr -> usr.active(true)
        );
    }

    @Override
    public UserDataResponse deactivateUser(Long id) {
        return changeUser(
                repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")),
                usr -> usr.active(false)
        );
    }

    @Override
    public UserDataResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        var user = repository.findByName(changePasswordRequest.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!encodePassword(changePasswordRequest.getOldPassword()).equals(user.getPassword())) {
            throw new UserParametersException("Old password incorrect");
        }

        return changeUser((user),
                usr -> usr.password(encodePassword(changePasswordRequest.getNewPassword())));
    }

    @Override
    public void deleteUser(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("User not found");
        }
    }

    private UserDataResponse changeUser(UserEntity user, Consumer<UserEntity.UserEntityBuilder> operation) {
        var userBuilder = user.toBuilder();
        operation.accept(userBuilder);
        return modelMapper.userEntityToUserData(repository.save(userBuilder.build()));
    }

    private String encodePassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
