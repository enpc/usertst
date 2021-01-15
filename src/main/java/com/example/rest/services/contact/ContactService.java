package com.example.rest.services.contact;

import com.example.rest.rest.contact.request.ContactRequest;
import com.example.rest.rest.contact.response.ContactResponse;

import java.util.Optional;

public interface ContactService {

    Optional<ContactResponse> getByUserName(String name);

    void setUserContacts(ContactRequest request);
}
