package com.example.rest.services.users;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordRequest {

    @ApiModelProperty(value = "User name", example = "User")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "Old password", example = "12345678")
    @NotBlank
    private String oldPassword;

    @ApiModelProperty(value = "New password", example = "87654321")
    @NotBlank
    @Size(min = 3)
    private String newPassword;
}
