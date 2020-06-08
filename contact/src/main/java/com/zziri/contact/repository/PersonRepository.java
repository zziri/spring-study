package com.zziri.contact.repository;


import com.zziri.contact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);                                           // Query Method, 이름이 같은 Person 객체를 모두 찾아 반환
    List<Person> findByBlockIsNull();                                               // Query Method, block되지 않은 Person 객체를 모두 반환
    List<Person> findByBloodType(String bloodType);
    @Query(value ="select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);
}
