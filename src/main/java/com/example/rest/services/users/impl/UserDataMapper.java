package com.example.rest.services.users.impl;

import com.example.rest.entityes.UserEntity;
import com.example.rest.services.users.UserDataResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataMapper {
    UserDataResponse userEntityToUserData(UserEntity entity);
}
