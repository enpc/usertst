package com.example.rest.rest.admin;

import com.example.rest.rest.admin.response.UserDataResponse;
import com.example.rest.services.users.UsersService;
import com.example.rest.rest.admin.request.CreateUserRequest;
import com.example.rest.rest.admin.request.SetUserDataRequest;
import com.example.rest.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersAdminControllerImpl implements UsersAdminController {

    private final UsersService usersService;

    @Override
    public UserDataResponse createUser(CreateUserRequest createUserRequest) {
        return usersService.create(createUserRequest);
    }

    @Override
    public List<UserDataResponse> getUsers() {
        return usersService.getAll();
    }

    @Override
    public UserDataResponse getUser(Long id) {
        return usersService.getById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDataResponse updateUser(Long id, SetUserDataRequest setUserDataRequest) {
        return usersService.updateUserData(id, setUserDataRequest);
    }

    @Override
    public UserDataResponse activateUser(Long id) {
        return usersService.activateUser(id);
    }

    @Override
    public UserDataResponse deactivateUser(Long id) {
        return usersService.deactivateUser(id);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
