package com.zziri.contact.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor                // @Data가 있어도 Constructor 관련 어노테이션은 있어야합니다(이유는 모름...)
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
}
