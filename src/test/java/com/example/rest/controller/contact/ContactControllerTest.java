package com.example.rest.controller.contact;

import com.example.rest.entityes.ContactEntity;
import com.example.rest.entityes.UserEntity;
import com.example.rest.repositoryes.ContactRepository;
import com.example.rest.repositoryes.UserRepository;
import com.example.rest.services.contact.ContactRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    private static final String user1Name = "User1";
    private static final String user1Password = "12345678";
    private static final String user1FirstName = "User1 F";
    private static final String user1LastName = "USer1 L";
    private static final String user1City = "Saratov";
    private static final String user1Address = "Lenina 1";

    private static final String user2Name = "User2";
    private static final String user2Password = "87654321";

    @Before
    public void setUp() {
        List<UserEntity> users = Arrays.asList(
                UserEntity.builder()
                        .id(1L)
                        .name(user1Name)
                        .password(user1Password)
                        .firstName(user1FirstName)
                        .lastName(user1LastName)
                        .build(),
                UserEntity.builder()
                        .id(2L)
                        .name(user2Name)
                        .password(user2Password)
                        .build()
        );

        var saved = userRepository.saveAll(users);

        var user1Id = saved.stream()
                .filter(u -> user1Name.equals(u.getName()))
                .findFirst().get().getId();


        contactRepository.save(ContactEntity.builder()
                .userId(user1Id)
                .city(user1City)
                .address(user1Address)
                .build()
        );
    }

    @After
    public void clear() {
        userRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    public void getContacts() throws Exception {
        mockMvc.perform(get("/contact")
                .param("name", user1Name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(user1FirstName))
                .andExpect(jsonPath("$.lastName").value(user1LastName))
                .andExpect(jsonPath("$.city").value(user1City))
                .andExpect(jsonPath("$.address").value(user1Address));
    }

    @Test
    public void getNotExistsUserContacts() throws Exception {
        mockMvc.perform(get("/contact")
                .param("name", "NotExistsUser"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getNotExistsContacts() throws Exception {
        mockMvc.perform(get("/contact")
                .param("name", user2Name))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void setContacts() throws Exception {
        final String city = "Moscow";
        final String address = "Lenina 2";

        var request = ContactRequest.builder()
                .userName(user2Name)
                .city(city)
                .address(address)
                .build();

        mockMvc.perform(post("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/contact")
                .param("name", user2Name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value(city))
                .andExpect(jsonPath("$.address").value(address));
    }

    @Test
    public void changeContacts() throws Exception {
        final String city = "Moscow";
        final String address = "Lenina 2";

        var request = ContactRequest.builder()
                .userName(user1Name)
                .city(city)
                .address(address)
                .build();

        mockMvc.perform(post("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/contact")
                .param("name", user1Name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(user1FirstName))
                .andExpect(jsonPath("$.lastName").value(user1LastName))
                .andExpect(jsonPath("$.city").value(city))
                .andExpect(jsonPath("$.address").value(address));
    }

    private byte[] convertObjectToJsonBytes(Object object) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
