package com.microdevs.userservice.web.controller;

import com.microdevs.userservice.model.CreateUser;
import com.microdevs.userservice.model.UpdateUser;
import com.microdevs.userservice.model.UserFilter;
import com.microdevs.userservice.model.dto.UserDto;
import com.microdevs.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUser createUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUser));
    }

    @GetMapping
    public Page<UserDto> getUser(UserFilter userFilter, Pageable pageable) {
        return userService.getUser(userFilter, pageable);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@PathVariable String phone, @RequestBody UpdateUser updateUser) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(phone, updateUser));
    }
    

}