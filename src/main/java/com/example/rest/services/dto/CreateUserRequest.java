package com.example.rest.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String name;

    @NotBlank()
    @Size(min = 3)
    private String password;
}
