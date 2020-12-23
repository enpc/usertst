package com.example.rest.services.dto;

import javax.validation.constraints.NotBlank;

public interface UserSetPropertiesRequest {
    @NotBlank
    String getName();

    String getFirstName();

    String getLastName();
}
