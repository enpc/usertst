package com.example.rest.services.users.impl;

import com.example.rest.entityes.UserEntity;
import com.example.rest.entityes.UsersRepository;
import com.example.rest.services.users.*;
import com.example.rest.services.users.exceptions.UserNotFoundException;
import com.example.rest.services.users.exceptions.UserParametersException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;

    private final UserDataMapper modelMapper;

    @Override
    public UserData create(CreateUserRequest request) {
        try {
            var user = UserEntity.builder()
                    .name(request.getName())
                    .active(true)
                    .password(encodePassword(request.getPassword()))
                    .build();
            return convertToData(repository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new UserParametersException("user with this name already exists");
        }
    }

    @Override
    public List<UserData> getAll() {
        return repository.findAll().stream()
                .map(this::convertToData)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserData> getById(Long id) {
        return repository.findById(id).map(this::convertToData);
    }

    @Override
    public Boolean login(LoginRequest loginRequest) {
        return repository.findByName(loginRequest.getName())
                .map(v -> v.getActive() && v.getPassword().equals(encodePassword(loginRequest.getPassword())))
                .orElse(false);
    }

    @Override
    public UserData updateUserData(Long id, SetUserDataRequest request) {
        return changeUser(
                repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")),
                usr -> usr
                        .name(request.getName())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName()));
    }

    @Override
    public UserData activateUser(Long id) {
        return changeUser(
                repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")),
                usr -> usr.active(true)
        );
    }

    @Override
    public UserData deactivateUser(Long id) {
        return changeUser(
                repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")),
                usr -> usr.active(false)
        );
    }

    @Override
    public UserData changePassword(ChangePasswordRequest changePasswordRequest) {
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

    private UserData changeUser(UserEntity user, Consumer<UserEntity.UserEntityBuilder> operation) {
        var userBuilder = user.toBuilder();
        operation.accept(userBuilder);
        return convertToData(repository.save(userBuilder.build()));
    }

    private UserData convertToData(UserEntity user) {
        return modelMapper.userEntityToUserData(user);
    }

    private String encodePassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
