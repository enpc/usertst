package com.example.rest.services.contact.impl;

import com.example.rest.entityes.ContactEntity;
import com.example.rest.services.contact.ContactRequest;
import com.example.rest.services.contact.ContactResponse;
import com.example.rest.services.users.UserDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(source = "userData.id", target = "userId")
    ContactEntity contactRequestToContactEntity(ContactRequest request, UserDataResponse userData);

    ContactResponse contactEntityToResponse(ContactEntity entity, UserDataResponse userData);
}
