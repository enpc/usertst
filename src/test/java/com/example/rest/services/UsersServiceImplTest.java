package com.example.rest.services;

import com.example.rest.entityes.UserEntity;
import com.example.rest.entityes.UsersRepository;
import com.example.rest.services.users.UsersService;
import com.example.rest.services.users.CreateUserRequest;
import com.example.rest.services.users.UserData;
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
    private UsersRepository usersRepository;

    @Test
    void create() {
        Mockito.when(usersRepository.save(any())).then(arg -> arg.getArgument(0));

        UserData user = usersService.create(new CreateUserRequest("Mike","123"));
        Mockito.verify(usersRepository).save(any());
        assertEquals("Mike", user.getName());
    }

    @Test
    void getAll_some() {
        Mockito.when(usersRepository.findAll())
                .thenReturn(Arrays.asList(
                        new UserEntity(1L,"user1","fn","ln","",true),
                        new UserEntity(1L,"user2","fn","ln","",true),
                        new UserEntity(1L,"user3","fn","ln","",true)
                ));

        var users = usersService.getAll();
        assertEquals(3, StreamSupport.stream(users.spliterator(), false).count() );
    }

    @Test
    void getAll_none() {
        Mockito.when(usersRepository.findAll())
                .thenReturn(new LinkedList<>());

        var users = usersService.getAll();
        assertEquals(0, StreamSupport.stream(users.spliterator(), false).count() );
    }


    @Test
    void getById() {

        Mockito.when(usersRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(usersRepository.findById(1L))
                .thenReturn(Optional.of(new UserEntity(1L,"user1","fn","ln","",true)));

        var existsUser = usersService.getById(1L);
        assertTrue(existsUser.isPresent());

        var notExistsUser = usersService.getById(5L);
        assertTrue(notExistsUser.isEmpty());
    }
}