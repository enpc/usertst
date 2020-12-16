package com.example.rest.services;

import com.example.rest.dao.UserDao;
import com.example.rest.dao.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDao create(String name) {
        if ((name == null) || (name.isEmpty())){
            throw new IllegalArgumentException("name can't be empty");
        }

        var user = new UserDao();
        user.setName(name);
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return repository.findAll().stream()
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(Long id) {
        return  repository.findById(id).map(User.class::cast);
    }

    @Override
    public Boolean userCanLogging(String name) {
        return repository.findByName(name)
                .map(UserDao::getActive)
                .orElse(false);
    }

    @Override
    public User updateUserData(Long id, UserDataRequest request) {
        var user = repository.getOne(id);
        user.setName(request.getName());
        return repository.save(user);
    }

    @Override
    public User activateUser(Long id) {
        return setUserActive(id, true);
    }

    @Override
    public User deactivateUser(Long id) {
        return setUserActive(id, false);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private User setUserActive(Long id, Boolean active){
        var user = repository.getOne(id);
        user.setActive(active);
        return repository.save(user);
    }
}
