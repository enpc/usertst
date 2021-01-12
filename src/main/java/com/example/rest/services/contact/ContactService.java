package com.example.rest.services.contact;

import java.util.Optional;

public interface ContactService {

    Optional<ContactResponse> getByUserName(String name);

    void setUserContacts(ContactRequest request);
}
