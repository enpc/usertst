package com.example.rest.controllers.dto;

import com.example.rest.services.dto.LoginRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequestDto implements LoginRequest {
    @ApiModelProperty(value = "User name", example = "User")
    String name;

    @ApiModelProperty(value = "User password", example = "password")
    String password;
}

