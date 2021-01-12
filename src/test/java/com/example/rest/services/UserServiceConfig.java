package com.example.rest.services;

import com.example.rest.repositoryes.UserRepository;
import com.example.rest.services.users.UsersService;
import com.example.rest.services.users.impl.UserDataMapper;
import com.example.rest.services.users.impl.UsersServiceImpl;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserServiceConfig {

    @Bean
    public UsersService usersService(){
        return new UsersServiceImpl(usersRepository(), modelMapper());
    }

    @Bean
    public UserRepository usersRepository(){
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public UserDataMapper modelMapper(){
        return Mappers.getMapper(UserDataMapper.class);
    }
}
