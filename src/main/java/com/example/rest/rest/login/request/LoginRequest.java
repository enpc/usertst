package com.example.rest.rest.login.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @ApiModelProperty(value = "User name", example = "User")
    @NotBlank
    String name;

    @ApiModelProperty(value = "User password", example = "password")
    @NotBlank
    String password;
}

