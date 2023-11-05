package com.microdevs.userservice.service;

import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.UserFilter;
import com.microdevs.userservice.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto createUser(CreateUser createUser);

    Page<UserDto> getUser(UserFilter userFilter, Pageable pageable);

    UserDto updateUser(String phone, UpdateUser updateUser);

    void terminateUser(String phone);

    void passiveUser(String phone);
}
