package com.example.rest.services.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSetPropertiesRequest {
    @NotBlank
    private String name;

    private String firstName;

    private String lastName;

}
