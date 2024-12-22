package com.company.pgi.controller.person;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.company.pgi.model.Person;

public interface IPersonController {
    List<Person> getAllPerson();
    ResponseEntity<Person> getPersonById(Integer id);
    Person createPerson(Person person);
    ResponseEntity<Person> updatePerson(Integer id, Person person);
    ResponseEntity<Void> deletePerson(Integer id);
}
