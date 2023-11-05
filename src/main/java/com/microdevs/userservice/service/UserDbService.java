package com.microdevs.userservice.service;

import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.UserFilter;
import com.microdevs.userservice.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDbService {
    UserDto create(CreateUser createUser);

    Page<UserDto> get(UserFilter userFilter, Pageable pageable);

    UserDto update(String phone, UpdateUser updateUser);
}
