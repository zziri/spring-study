package com.zziri.contact.service;

import com.zziri.contact.domain.Person;
import com.zziri.contact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j                  // log.info() 사용을 위함
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlocks() {
        return personRepository.findByBlockIsNull();                // block되지 않은 객체들만 반환
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).get();

        log.info("person : {}", person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {
        return personRepository.findByName(name);
    }
}
