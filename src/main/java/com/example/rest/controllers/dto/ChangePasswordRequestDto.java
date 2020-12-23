package com.example.rest.controllers.dto;

import com.example.rest.services.dto.ChangePasswordRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChangePasswordRequestDto implements ChangePasswordRequest {

    @ApiModelProperty(value = "User name", example = "User")
    private String name;

    @ApiModelProperty(value = "Old password", example = "12345678")
    private String oldPassword;

    @ApiModelProperty(value = "New password", example = "87654321")
    private String newPassword;
}
