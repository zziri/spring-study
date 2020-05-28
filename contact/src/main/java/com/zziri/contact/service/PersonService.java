package com.zziri.contact.service;

import com.zziri.contact.domain.Person;
import com.zziri.contact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlocks() {
        List<Person> people = personRepository.findAll();

        return people.stream().filter(person -> person.getBlock() == null)
                .collect(Collectors.toList());                                                      // block되지 않은 객체들만 반환
    }
}
