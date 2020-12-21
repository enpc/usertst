package com.example.rest.services;

import com.example.rest.dao.UsersRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserServiceConfig {

    @Bean
    public UsersService usersService(){
        return new UsersServiceImpl(usersRepository());
    }

    @Bean
    public UsersRepository usersRepository(){
        return Mockito.mock(UsersRepository.class);
    }
}
