package com.company.pgi.controller.person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Person;
import com.company.pgi.service.person.IPersonService;


@RestController
@RequestMapping("/api/person")
public class PersonController implements IPersonController {
    @Autowired
    private IPersonService iPersonService;

    @Override
    @GetMapping("/list")
    public List<Person> getAllPerson() {
        return iPersonService.getAllPerson();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        Optional<Person> person = iPersonService.getPersonById(id);
        return person.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return iPersonService.savePerson(person);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        Optional<Person> existingPerson = iPersonService.getPersonById(id);
        if( existingPerson.isPresent()){
            person.setId(id);
            return ResponseEntity.ok(iPersonService.savePerson(person));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        Optional<Person> existingPerson = iPersonService.getPersonById(id);
        if( existingPerson.isPresent()){
            iPersonService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
