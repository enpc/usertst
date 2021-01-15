package com.example.rest.rest.contact;

import com.example.rest.rest.contact.request.ContactRequest;
import com.example.rest.rest.contact.response.ContactResponse;
import com.example.rest.services.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ContactControllerImpl implements ContactController {

    private final ContactService contactService;

    @Override
    public ResponseEntity<Void> setUserContacts(@Valid ContactRequest request) {
        contactService.setUserContacts(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ContactResponse> getUserContacts(String name) {
        return contactService.getByUserName(name)
                .map(contactResponse -> ResponseEntity.ok(contactResponse))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
