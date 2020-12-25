package com.example.rest.services;

import com.example.rest.dao.UserDao;
import com.example.rest.dao.UsersRepository;
import com.example.rest.services.dto.*;
import com.example.rest.services.exceptions.UserNotFoundException;
import com.example.rest.services.exceptions.UserParametersException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public UserProperties create(CreateUserRequest request) {
        try {
            var user = UserDao.builder()
                    .name(request.getName())
                    .active(true)
                    .password(encodePassword(request.getPassword()))
                    .build();
            return convertToProperties(repository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new UserParametersException("user with this name already exists");
        }
    }

    @Override
    public Iterable<UserProperties> getAll() {
        return repository.findAll().stream()
                .map(this::convertToProperties)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserProperties> getById(Long id) {
        return repository.findById(id).map(this::convertToProperties);
    }

    @Override
    public Boolean login(LoginRequest loginRequest) {
        return repository.findByName(loginRequest.getName())
                .map(v -> v.getActive() && v.getPassword().equals(encodePassword(loginRequest.getPassword())))
                .orElse(false);
    }

    @Override
    public UserProperties updateUserData(Long id, UserSetPropertiesRequest request) {
        return changeUser(repository.findById(id), usr -> {
            usr
                    .name(request.getName())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName());
        });
    }

    @Override
    public UserProperties activateUser(Long id) {
        return changeUser(repository.findById(id), usr -> usr.active(true));
    }

    @Override
    public UserProperties deactivateUser(Long id) {
        return changeUser(repository.findById(id), usr -> usr.active(false));
    }

    @Override
    public UserProperties ChangePassword(ChangePasswordRequest changePasswordRequest) {
        var user = repository.findByName(changePasswordRequest.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!encodePassword(changePasswordRequest.getOldPassword()).equals(user.getPassword())) {
            throw new UserParametersException("Old password incorrect");
        }

        return changeUser(Optional.of(user),
                usr -> usr.password(encodePassword(changePasswordRequest.getNewPassword())));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private UserProperties changeUser(Optional<UserDao> user, Consumer<UserDao.UserDaoBuilder> operation) {
        var userBuilder = user
                .map(usr -> usr.toBuilder())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        operation.accept(userBuilder);
        return convertToProperties(repository.save(userBuilder.build()));
    }

    private UserProperties convertToProperties(UserDao user) {
        return modelMapper.map(user, UserProperties.class);
    }

    private String encodePassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
