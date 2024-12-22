package com.company.pgi.service.person;

import java.util.List;
import java.util.Optional;

import com.company.pgi.model.Person;

public interface  IPersonService {
    List<Person> getAllPerson();
    Optional<Person> getPersonById(Integer id);
    Person savePerson(Person person);
    void deletePerson (Integer id);
}
