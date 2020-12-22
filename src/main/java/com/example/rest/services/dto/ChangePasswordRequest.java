package com.example.rest.services.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min = 3)
    private String newPassword;
}
