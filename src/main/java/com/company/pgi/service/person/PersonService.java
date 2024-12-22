package com.company.pgi.service.person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pgi.model.Person;
import com.company.pgi.repository.person.IPersonRepository;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository iPersonRepository;

    @Override
    public List<Person> getAllPerson() {
        return iPersonRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(Integer id) {
        return iPersonRepository.findById(id);
    }

    @Override
    public com.company.pgi.model.Person savePerson(Person person) {
        return iPersonRepository.save(person);
    }

    @Override
    public void deletePerson(Integer id) {
        iPersonRepository.deleteById(id);
    }
}
