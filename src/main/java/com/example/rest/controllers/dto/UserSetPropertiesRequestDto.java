package com.example.rest.controllers.dto;

import com.example.rest.services.dto.UserSetPropertiesRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSetPropertiesRequestDto implements UserSetPropertiesRequest {

    @ApiModelProperty(value = "name", example = "User")
    private String name;

    @ApiModelProperty(value = "first name", example = "FirstName")
    private String firstName;

    @ApiModelProperty(value = "last name", example = "LatName")
    private String lastName;

}
