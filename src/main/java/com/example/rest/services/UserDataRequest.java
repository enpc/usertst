package com.example.rest.services;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDataRequest {
    @NotBlank
    private String name;
}
