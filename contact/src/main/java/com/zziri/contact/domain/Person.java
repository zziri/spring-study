package com.zziri.contact.domain;

import com.zziri.contact.controller.dto.PersonDto;
import com.zziri.contact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data                               // @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor를 포함합니다
@Where(clause = "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull                                // 생성자로 초기화하는 변수에 이 변수를 포함합니다
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude                       // @ToString 어노테이션으로 toString()의 결과에 표시하는 정보에서 이 변수를 제외합니다
    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // 다시 공부하고 정리, Person도 Block도 DB에 들어가는 entity라서 해줘야하는 설정인 것으로 기억
    @ToString.Exclude
    private Block block;

    public void set(PersonDto personDto) {

        if (!StringUtils.isEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
        }

        if(!StringUtils.isEmpty(personDto.getBloodType())) {
            this.setBloodType(personDto.getBloodType());
        }

        if(!StringUtils.isEmpty(personDto.getAddress())) {
            this.setAddress(personDto.getAddress());
        }

        if(!StringUtils.isEmpty(personDto.getJob())) {
            this.setJob(personDto.getJob());
        }

        if(!StringUtils.isEmpty(personDto.getPhoneNumber())) {
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
    }

    public Integer getAge() {
        if (this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        } else {
            return null;
        }
    }

    public boolean isBirthdayToday() {
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }
}
