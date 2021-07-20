package com.example.mvc.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private String phoneNumber;
    private String address;
}
