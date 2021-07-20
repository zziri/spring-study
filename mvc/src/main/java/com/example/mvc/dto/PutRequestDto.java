package com.example.mvc.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PutRequestDto {
    private String name;
    private int age;
    private List<CarDto> carList;
}
