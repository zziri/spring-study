package com.example.mvc.controller;


import com.example.mvc.dto.UserReq;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
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
}
