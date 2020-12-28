package com.example.rest.rest.admin;

import com.example.rest.services.users.UsersService;
import com.example.rest.services.users.CreateUserRequest;
import com.example.rest.services.users.SetUserDataRequest;
import com.example.rest.services.users.UserData;
import com.example.rest.services.users.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersAdminControllerImpl implements UsersAdminController {

    private final UsersService usersService;

    @Override
    public UserData createUser(CreateUserRequest createUserRequest) {
        return usersService.create(createUserRequest);
    }

    @Override
    public List<UserData> getUsers() {
        return usersService.getAll();
    }

    @Override
    public UserData getUser(Long id) {
        return usersService.getById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserData updateUser(Long id, SetUserDataRequest setUserDataRequest) {
        return usersService.updateUserData(id, setUserDataRequest);
    }

    @Override
    public UserData activateUser(Long id) {
        return usersService.activateUser(id);
    }

    @Override
    public UserData deactivateUser(Long id) {
        return usersService.deactivateUser(id);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
