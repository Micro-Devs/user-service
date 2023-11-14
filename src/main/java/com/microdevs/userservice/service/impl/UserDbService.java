package com.microdevs.userservice.service.impl;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.userservice.constant.UserConstant;
import com.microdevs.userservice.entity.QUser;
import com.microdevs.userservice.entity.User;
import com.microdevs.userservice.exception.UserNotFoundException;
import com.microdevs.userservice.exception.UserSuspendedException;
import com.microdevs.userservice.exception.UserTerminatedException;
import com.microdevs.userservice.mapper.UserMapper;
import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.UserFilter;
import com.microdevs.userservice.model.dto.UserDto;
import com.microdevs.userservice.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService {
    private final UserMapper mapper;
    private final UserRepository repository;

    @Transactional
    public UserDto create(CreateUser createUser) {
        User createdEntity = repository.save(mapper.createToEntity(createUser));
        return mapper.entityToDto(createdEntity);
    }


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

    @Transactional
    public UserDto update(String phone, UpdateUser updateUser) {
        Optional<User> optionalEntity = Optional.ofNullable(repository.findByPhone(phone).orElseThrow(() ->
                new UserNotFoundException(UserConstant.ERROR_MESSAGE_USER_NOT_FOUND, UserConstant.ERROR_CODE_USER_NOT_FOUND, UserConstant.ERROR_DETAILED_MESSAGE_USER_NOT_FOUND_WITH_PHONE)));
        User updatedProduct = mapper.updateToEntity(optionalEntity.get(), updateUser);
        return mapper.entityToDto(repository.save(updatedProduct));
    }
    @Transactional
    public void checkStatusAndTerminate(String phone) {
        Optional<User> optionalEntity = Optional.ofNullable(repository.findByPhoneAndStatusNot(phone, StatusType.TERMINATED).orElseThrow(() ->
                new UserTerminatedException(UserConstant.ERROR_MESSAGE_USER_STATUS_TERMINATED, UserConstant.ERROR_CODE_USER_STATUS_TERMINATED, UserConstant.ERROR_DETAILED_MESSAGE_USER_STATUS_TERMINATED)));
        repository.save(mapper.statusToEntity(optionalEntity.get(), StatusType.TERMINATED));
    }
    @Transactional
    public void checkStatusAndSuspend(String phone) {
        Optional<User> optionalEntity = Optional.ofNullable(repository.findByPhoneAndStatusNot(phone, StatusType.TERMINATED).orElseThrow(() ->
                new UserTerminatedException(UserConstant.ERROR_MESSAGE_USER_STATUS_TERMINATED, UserConstant.ERROR_CODE_USER_STATUS_TERMINATED, UserConstant.ERROR_DETAILED_MESSAGE_USER_STATUS_TERMINATED)));
        if (StatusType.SUSPEND.equals(optionalEntity.get().getStatus()))
            throw new UserSuspendedException(UserConstant.ERROR_MESSAGE_USER_STATUS_SUSPENDED, UserConstant.ERROR_CODE_USER_STATUS_SUSPENDED, UserConstant.ERROR_DETAILED_MESSAGE_USER_STATUS_SUSPENDED);

        repository.save(mapper.statusToEntity(optionalEntity.get(), StatusType.SUSPEND));
    }
}
