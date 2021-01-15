package com.example.rest.services.contact.impl;

import com.example.rest.repositoryes.ContactRepository;
import com.example.rest.rest.contact.request.ContactRequest;
import com.example.rest.rest.contact.response.ContactResponse;
import com.example.rest.services.contact.ContactService;
import com.example.rest.services.users.UsersService;
import com.example.rest.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final UsersService usersService;

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    @Override
    public Optional<ContactResponse> getByUserName(String name) {
        var user = usersService.getByName(name)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("user with name: %s not found", name)
                        ));

        var contact = contactRepository.findById(user.getId());

        return contact.map(contactEntity -> contactMapper.contactEntityToResponse(contactEntity, user));
    }

    @Override
    public void setUserContacts(ContactRequest request) {
        var user = usersService.getByName(request.getUserName())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("user with name: %s not found", request.getUserName())
                        ));

        var contactEntity = contactMapper.contactRequestToContactEntity(request, user);
        contactRepository.save(contactEntity);
    }
}
