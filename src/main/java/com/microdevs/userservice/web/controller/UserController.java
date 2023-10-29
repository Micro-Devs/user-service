package com.microdevs.userservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @PostMapping("/log")
    public void log(){
        log.info("---------------   log method running");
        System.out.println("---------------- log method running");
    }
}
