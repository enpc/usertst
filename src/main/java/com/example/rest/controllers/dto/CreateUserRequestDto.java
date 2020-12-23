package com.example.rest.controllers.dto;

import com.example.rest.services.dto.CreateUserRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequestDto implements CreateUserRequest {

    @ApiModelProperty(value = "User name", example = "User")
    private String name;

    @ApiModelProperty(value = "password", example = "12345678")
    private String password;
}
