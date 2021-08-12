package com.example.mvc.controller;


import com.example.mvc.annotation.Timer;
import com.example.mvc.dto.PostRequestDto;
import com.example.mvc.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
    @Transactional
    @GetMapping(path = "/hello")
    public String hello() {
        return "hello spring boot!";
    }

    @GetMapping(path = "/get/hello")
    public String getHello() {
        return "get Hello";
    }

    @RequestMapping(path = "/get/hi", method = RequestMethod.GET)
    public String hi() {
        return "hi";
    }

    @GetMapping(path = "/get/path-variable/{name}")
    public String pathVariable(@PathVariable("name") String name) {
        System.out.println("PathVariable : " + name);
        return name;
    }

    @GetMapping(path = "/get/query-param")
    public String queryParam(@RequestParam Map<String, String> param) {
        StringBuilder builder = new StringBuilder();
        param.forEach((key, value) -> builder.append(String.format("%s = %s\n", key, value)));
        return builder.toString();
    }

    @PostMapping(path = "/post")
    public void post(@RequestBody PostRequestDto dto) {
        System.out.println(dto);
    }

    @PutMapping(path = "/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }


    @Timer
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable Long userId, @RequestParam String account) throws InterruptedException {
        // db logic
        Thread.sleep(1000 * 2);
    }

    // TEXT
    @GetMapping(path = "/text")
    public String text(@RequestParam String account) {
        return account;
    }

    // JSON
    @PostMapping(path = "/json")
    public User json(@RequestBody User user) {
        return user;
    }
}
