package com.example.rest.services;

import com.example.rest.entityes.UserEntity;
import com.example.rest.repositoryes.UserRepository;
import com.example.rest.services.users.UsersService;
import com.example.rest.rest.admin.request.CreateUserRequest;
import com.example.rest.rest.admin.response.UserDataResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceConfig.class)
class UsersServiceImplTest {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void create() {
        Mockito.when(userRepository.save(any())).then(arg -> arg.getArgument(0));

        UserDataResponse user = usersService.create(new CreateUserRequest("Mike","123"));
        Mockito.verify(userRepository).save(any());
        assertEquals("Mike", user.getName());
    }

    @Test
    void getAll_some() {
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(
                        new UserEntity(1L,"user1","fn","ln","",true),
                        new UserEntity(1L,"user2","fn","ln","",true),
                        new UserEntity(1L,"user3","fn","ln","",true)
                ));

        var users = usersService.getAll();
        assertEquals(3, users.size());
    }

    @Test
    void getAll_none() {
        Mockito.when(userRepository.findAll())
                .thenReturn(new LinkedList<>());

        var users = usersService.getAll();
        assertEquals(0, StreamSupport.stream(users.spliterator(), false).count() );
    }


    @Test
    void getById() {

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(new UserEntity(1L,"user1","fn","ln","",true)));

        var existsUser = usersService.getById(1L);
        assertTrue(existsUser.isPresent());

        var notExistsUser = usersService.getById(5L);
        assertTrue(notExistsUser.isEmpty());
    }
}