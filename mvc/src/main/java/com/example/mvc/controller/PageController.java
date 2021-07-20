package com.example.mvc.controller;

import com.example.mvc.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping(path = "/main")
    public String main() {
        return "main.html";
    }

    // 추천하지 않는 방법, JSON Response 가 필요하다면 RestController 로 하기를,,
    @ResponseBody
    @GetMapping(path = "/user")
    public User user() {
        var user = User.builder()
                .name("steve")
                .address("suwon").build();
        return user;
    }
}
