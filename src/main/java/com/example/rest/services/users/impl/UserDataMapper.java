package com.example.rest.services.users.impl;

import com.example.rest.entityes.UserEntity;
import com.example.rest.services.users.UserData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataMapper {
    UserData userEntityToUserData(UserEntity entity);
}
