package com.example.rest.services;

import com.example.rest.dao.UserDao;
import com.example.rest.dao.UsersRepository;
import com.example.rest.services.dto.*;
import com.example.rest.services.exceptions.UserNotFoundException;
import com.example.rest.services.exceptions.UserParametersException;
import lombok.RequiredArgsConstructor;
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
            var user = new UserDao();
            user.setName(request.getName());
            user.setActive(true);
            user.setPassword(encodePassword(request.getPassword()));
            return convertToProperties(repository.save(user));
        }catch (DataIntegrityViolationException e){
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
        return  repository.findById(id).map(this::convertToProperties);
    }

    @Override
    public Boolean login(LoginRequest loginRequest) {
        return repository.findByName(loginRequest.getName())
                .map(v -> v.getActive() && v.getPassword().equals(encodePassword(loginRequest.getPassword())))
                .orElse(false);
    }

    @Override
    public UserProperties updateUserData(Long id, UserSetPropertiesRequest request) {
        return changeUser(id, usr -> {
            usr.setName(request.getName());
            usr.setFirstName(request.getFirstName());
            usr.setLastName(request.getLastName());
        });
    }

    @Override
    public UserProperties activateUser(Long id) {
        return changeUser(id, usr -> usr.setActive(true));
    }

    @Override
    public UserProperties deactivateUser(Long id) {
        return changeUser(id, usr -> usr.setActive(false));
    }

    @Override
    public UserProperties ChangePassword(ChangePasswordRequest changePasswordRequest) {
        var user = repository.findByName(changePasswordRequest.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!encodePassword(changePasswordRequest.getOldPassword()).equals(user.getPassword())){
            throw new UserParametersException("Old password incorrect");
        }

        user.setPassword(encodePassword(changePasswordRequest.getNewPassword()));
        return convertToProperties(repository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private UserProperties changeUser(Long id, Consumer<UserDao> operation){
        var user = repository.getOne(id);
        operation.accept(user);
        return convertToProperties(repository.save(user));
    }

    private UserProperties convertToProperties(UserDao user){
        return modelMapper.map(user, UserProperties.class);
    }

    private String encodePassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
