package com.microdevs.userservice.mapper;

import com.microdevs.userservice.entity.User;
import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.dto.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public User createToEntity(CreateUser createUser);

    public UserDto entityToDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateToEntity(@MappingTarget User product, UpdateUser updateUser);
}
