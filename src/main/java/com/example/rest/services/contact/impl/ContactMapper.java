package com.example.rest.services.contact.impl;

import com.example.rest.entityes.ContactEntity;
import com.example.rest.rest.contact.request.ContactRequest;
import com.example.rest.rest.contact.response.ContactResponse;
import com.example.rest.rest.admin.response.UserDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(source = "userData.id", target = "userId")
    ContactEntity contactRequestToContactEntity(ContactRequest request, UserDataResponse userData);

    ContactResponse contactEntityToResponse(ContactEntity entity, UserDataResponse userData);
}
