package com.example.rest.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersServiceConfig {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
