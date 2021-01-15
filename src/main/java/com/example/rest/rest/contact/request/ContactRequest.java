package com.example.rest.rest.contact.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ContactRequest {

    @NotNull
    private String userName;

    @NotNull
    private String city;

    @NotNull
    private String address;
}
