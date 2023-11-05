package com.microdevs.userservice.service.impl;

import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.UserFilter;
import com.microdevs.userservice.model.dto.UserDto;
import com.microdevs.userservice.service.UserDbService;
import com.microdevs.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDbService userDbService;

    @Override
    public UserDto createUser(CreateUser createUser) {
        return userDbService.create(createUser);
    }

    @Override
    public Page<UserDto> getUser(UserFilter userFilter, Pageable pageable) {
        return userDbService.get(userFilter, pageable);
    }

    @Override
    public UserDto updateUser(String phone, UpdateUser updateUser) {
        return userDbService.update(phone, updateUser);
    }
}
