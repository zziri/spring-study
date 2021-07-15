package com.zziri.contact.controller;

import com.zziri.contact.controller.dto.CustomParameter;
import com.zziri.contact.controller.dto.PersonDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterController {
    @GetMapping(path = "/parameter")
    public CustomParameter parameter(CustomParameter parameter) {
        return parameter;
    }

    @GetMapping(path = "/param")
    public PersonDto param(PersonDto personDto) {
        return personDto;
    }
}
