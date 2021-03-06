package com.example.rest.rest.admin.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class SetUserDataRequest {

    @ApiModelProperty(value = "name", example = "User")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "first name", example = "FirstName")
    private String firstName;

    @ApiModelProperty(value = "last name", example = "LatName")
    private String lastName;

}
