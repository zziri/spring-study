package com.example.mvc.controller;


import com.example.mvc.dto.UserReq;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
    @GetMapping
    public UserReq user(UserReq req) {
        return req;
    }
}
