package com.example.rest.services.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface ChangePasswordRequest {
    @NotBlank
    String getName();

    @NotBlank
    String getOldPassword();

    @NotBlank
    @Size(min = 3)
    String getNewPassword();
}
