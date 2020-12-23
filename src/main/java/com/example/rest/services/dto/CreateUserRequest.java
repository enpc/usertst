package com.example.rest.services.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface CreateUserRequest {
    @NotBlank
    String getName();

    @NotBlank()
    @Size(min = 3)
    String getPassword();
}
