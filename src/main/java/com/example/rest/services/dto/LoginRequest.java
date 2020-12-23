package com.example.rest.services.dto;

import javax.validation.constraints.NotBlank;

public interface LoginRequest {
    @NotBlank
    String getName();

    @NotBlank
    String getPassword();
}
