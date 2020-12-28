package com.example.rest.services.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    private Long id;

    private String name;

    private String firstName;

    private String lastName;

    private Boolean active;
}
