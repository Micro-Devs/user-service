package com.microdevs.userservice.mapper;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.userservice.entity.User;
import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.dto.UserDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public User createToEntity(CreateUser createUser);

    public UserDto entityToDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateToEntity(@MappingTarget User product, UpdateUser updateUser);

    User dtoToEntity(UserDto userDto);

    @Mapping(target = "status", source = "status")
    User statusToEntity(@MappingTarget User user, StatusType status);

    List<UserDto> entityListtoDtoList(List<User> userList);
}
