package com.zziri.contact.controller.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomParameter {
    String name;
    Long id;
    LocalDate date;

    public static CustomParameter of(String name, String id, String date) {
        CustomParameter ret = new CustomParameter();
        if (name != null)
            ret.setName(name);
        if (id != null && !id.isEmpty())
            ret.setId(Long.valueOf(id));
        if (date != null && !date.isEmpty())
            ret.setDate(LocalDate.parse(date));
        return ret;
    }
}
