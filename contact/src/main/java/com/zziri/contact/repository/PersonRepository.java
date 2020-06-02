package com.zziri.contact.repository;


import com.zziri.contact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);                                           // Query Method, 이름이 같은 Person 객체를 모두 찾아 반환
    List<Person> findByBlockIsNull();                                               // Query Method, block되지 않은 Person 객체를 모두 반환
    List<Person> findByBloodType(String bloodType);
    List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);
}
