package com.zziri.contact.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data                                       // @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor를 포함합니다
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull                                // 생성자로 초기화하는 변수에 이 변수를 포함합니다
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude                       // @ToString 어노테이션으로 toString()의 결과에 표시하는 정보에서 이 변수를 제외합니다
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)          // 다시 공부하고 정리, Person도 Block도 DB에 들어가는 entity라서 해줘야하는 설정인 것으로 기억
    @ToString.Exclude
    private Block block;
}
