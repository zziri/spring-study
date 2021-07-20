package com.example.mvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRequestDto {
    private String account;
    private String email;
    private String address;
    private String password;
}
