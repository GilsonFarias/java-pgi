package com.company.pgi.service.person;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.pgi.model.Person;

@Service
public interface  IPersonService {

    Person savePerson(Person person);
    
    String deletePerson (Long id);

    Person getPersonById(Long id);

    List<Person> getPresonList();
}
