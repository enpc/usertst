package com.example.rest.rest.admin.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse {

    private Long id;

    private String name;

    private String firstName;

    private String lastName;

    private Boolean active;
}
