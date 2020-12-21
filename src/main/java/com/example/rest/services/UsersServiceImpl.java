package com.example.rest.services;

import com.example.rest.dao.UserDao;
import com.example.rest.dao.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersRepository repository;

    @Override
    public UserDao create(String name) {
        if ((name == null) || (name.isEmpty())) {
            throw new UserParametersException("name can't be empty");
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
        return changeUser(id, usr -> usr.setName(request.getName()));
    }

    @Override
    public User activateUser(Long id) {
        return changeUser(id, usr -> usr.setActive(true));
    }

    @Override
    public User deactivateUser(Long id) {
        return changeUser(id, usr -> usr.setActive(false));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private User changeUser(Long id, Consumer<UserDao> operation){
        var user = repository.getOne(id);
        operation.accept(user);
        return repository.save(user);
    }
}
