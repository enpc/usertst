package com.example.rest.services;

import com.example.rest.dao.UserDao;
import com.example.rest.dao.UsersRepository;
import com.sun.source.tree.ModuleTree;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import static org.mockito.ArgumentMatchers.argThat;

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

        User user = usersService.create("Mike");
        Mockito.verify(usersRepository).save(any());
        assertEquals("Mike", user.getName());
    }

    @Test
    void create_emptyName() {
        Mockito.when(usersRepository.save(any())).then(arg -> arg.getArgument(0));

        Assert.assertThrows(IllegalArgumentException.class,() -> usersService.create(""));
    }

    @Test
    void getAll_some() {
        Mockito.when(usersRepository.findAll())
                .thenReturn(Arrays.asList(
                        new UserDao(1L,"user1",true),
                        new UserDao(2L,"user2", false),
                        new UserDao(3L,"user3",true)
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
                .thenReturn(Optional.of(new UserDao(1L, "user1", true)));

        var existsUser = usersService.getById(1L);
        assertTrue(existsUser.isPresent());

        var notExistsUser = usersService.getById(5L);
        assertTrue(notExistsUser.isEmpty());
    }
}