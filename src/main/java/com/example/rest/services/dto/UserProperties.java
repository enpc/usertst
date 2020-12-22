package com.example.rest.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProperties {

    private Long id;

    private String name;

    private String firstName;

    private String lastName;

    private Boolean active;
}
