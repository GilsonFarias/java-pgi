package com.company.pgi.controller.person;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.dto.RequestBase;
import com.company.pgi.model.Person;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.service.person.IPersonService;


@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private IPersonService iPersonService;
   
    @PostMapping("/list2")
    public ResponseBase<Person> getList2( @RequestBody RequestBase<Person> requestBase) {

        return iPersonService.getPresonList(requestBase);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = iPersonService.getPersonById(id);
        return person.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseBase<Person> createPerson(@RequestBody Person person) {

        return iPersonService.savePerson(person);
    }


    // @PutMapping("/{id}")
    // public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
    //     Optional<Person> existingPerson = iPersonService.getPersonById(id);
    //     if( existingPerson.isPresent()){
    //         person.setId(id);
    //         return ResponseEntity.ok(iPersonService.savePerson(person));
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        Optional<Person> existingPerson = iPersonService.getPersonById(id);
        if( existingPerson.isPresent()){
            iPersonService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
