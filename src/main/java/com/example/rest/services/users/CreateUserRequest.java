package com.example.rest.services.users;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreateUserRequest {

    @ApiModelProperty(value = "User name", example = "User")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "password", example = "12345678")
    @NotBlank()
    @Size(min = 3)
    private String password;
}
