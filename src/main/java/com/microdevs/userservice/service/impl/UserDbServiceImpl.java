package com.microdevs.userservice.service.impl;

import com.microdevs.baseservice.exception.UserNotFoundException;
import com.microdevs.userservice.constant.UserConstant;
import com.microdevs.userservice.entity.QUser;
import com.microdevs.userservice.entity.User;
import com.microdevs.userservice.mapper.UserMapper;
import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.UserFilter;
import com.microdevs.userservice.model.dto.UserDto;
import com.microdevs.userservice.repository.UserRepository;
import com.microdevs.userservice.service.UserDbService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbServiceImpl implements UserDbService {
    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public UserDto create(CreateUser createUser) {
        User createdEntity = repository.save(mapper.createToEntity(createUser));
        return mapper.entityToDto(createdEntity);
    }

    @Override
    public Page<UserDto> get(UserFilter userFilter, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QUser qUser = QUser.user;

        if (Objects.nonNull(userFilter.getEmail()))
            builder.and(qUser.email.eq(userFilter.getEmail()));
        if (Objects.nonNull(userFilter.getPhone()))
            builder.and(qUser.phone.eq(userFilter.getPhone()));
        if (Objects.nonNull(userFilter.getStatus()))
            builder.and(qUser.status.eq(userFilter.getStatus()));

        Predicate predicate = builder.getValue();
        return repository.findAll(predicate, pageable).map(mapper::entityToDto);
    }

    @Override
    public UserDto update(String phone, UpdateUser updateUser) {
        Optional<User> existingProduct = Optional.ofNullable(repository.findByPhone(phone).orElseThrow(() ->
                new UserNotFoundException("User Not Found", UserConstant.ERROR_USER_NOT_FOUND, "User does not exist with the given phone number")));
        User updatedProduct = mapper.updateToEntity(existingProduct.get(), updateUser);
        return mapper.entityToDto(repository.save(updatedProduct));
    }
}
