package com.example.rest.services;

import com.example.rest.dao.UsersRepository;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserServiceConfig {

    @Bean
    public UsersService usersService(){
        return new UsersServiceImpl(usersRepository(), modelMapper());
    }

    @Bean
    public UsersRepository usersRepository(){
        return Mockito.mock(UsersRepository.class);
    }

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
