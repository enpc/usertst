package com.example.rest.controllers.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @ApiModelProperty(value = "User name", example = "User")
    @NotBlank
    String name;
}

