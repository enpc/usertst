package com.example.rest.rest.contact.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContactResponse {

    private final String firstName;

    private final String lastName;

    private final String city;

    private final String address;
}
