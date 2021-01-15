package com.example.rest.controller.admin;

import com.example.rest.entityes.UserEntity;
import com.example.rest.repositoryes.UserRepository;
import com.example.rest.rest.admin.request.CreateUserRequest;
import com.example.rest.rest.admin.request.SetUserDataRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private Long user1Id;
    private static final String user1Name = "User1";
    private static final String user1Password = "12345678";

    private static final String user2Name = "User2";
    private static final String user2Password = "87654321";

    @Before
    public void setUp() {
        List<UserEntity> users = Arrays.asList(
                UserEntity.builder()
                        .id(1L)
                        .name(user1Name)
                        .password(user1Password)
                        .build(),
                UserEntity.builder()
                        .id(2L)
                        .name(user2Name)
                        .password(user2Password)
                        .build()
        );

        var saved = userRepository.saveAll(users);
        user1Id = saved.stream()
                .filter(u -> user1Name.equals(u.getName()))
                .findFirst().get().getId();
    }

    @After
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser() throws Exception {

        final String userName = "User";
        final String password = "12345678";

        var createUserRequest = CreateUserRequest.builder()
                .name(userName)
                .password(password)
                .build();

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(createUserRequest))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userName));
    }

    @Test
    public void createExistsUser() throws Exception {
        var createUserRequest = CreateUserRequest.builder()
                .name(user1Name)
                .password(user1Password)
                .build();

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(createUserRequest))
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createUserBadParameters() throws Exception {

        final String userName = "User";
        final String password = "";

        var createUserRequest = CreateUserRequest.builder()
                .name(userName)
                .password(password)
                .build();

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(createUserRequest))
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void userActivating() throws Exception{
        mockMvc.perform(post("/admin/{Id}/deactivate", user1Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(false));

        mockMvc.perform(post("/admin/{Id}/activate", user1Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    public void setUserData() throws Exception {
        final String name = "NewName";
        final String firstName = "First Name";
        final String lastName = "Last Name";

        var setUserDataRequest = SetUserDataRequest.builder()
                .name(name)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        mockMvc.perform(post("/admin/{id}", user1Id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(setUserDataRequest))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1Id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));
    }

    @Test
    public void deleteUser() throws Exception{
        mockMvc.perform(delete("/admin/{id}", user1Id))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/admin/{id}", user1Id))
                .andExpect(status().is4xxClientError());
    }

    private byte[] convertObjectToJsonBytes(Object object) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
