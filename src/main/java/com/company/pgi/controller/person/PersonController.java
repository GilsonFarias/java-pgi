package com.company.pgi.controller.person;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Person;
import com.company.pgi.model.dto.PersonDto;
import com.company.pgi.service.Mapper.PersonMapperComponet;
import com.company.pgi.service.person.IPersonService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final IPersonService iPersonService;
    private final PersonMapperComponet personMapperComponet;

    public PersonController(IPersonService iPersonService, PersonMapperComponet personMapperComponet) {
        this.iPersonService = iPersonService;
        this.personMapperComponet = personMapperComponet;
    }
   
    @PostMapping("/list")
    public ResponseEntity<List<Person>> getList() {

        var personList = iPersonService.getPresonList();

        return ResponseEntity.ok(personList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {

        var person = iPersonService.getPersonById(id);

        return ResponseEntity.ok(person);
    }

    @PostMapping("/insert")
    public ResponseEntity<Person> insertPerson(@RequestBody @Valid PersonDto personDto) {

        Person pInput = personMapperComponet.toEntity(personDto);

        Person pOutput = iPersonService.savePerson(pInput);

        return ResponseEntity.ok(pOutput);
    }

    @PostMapping("/edit")
    public ResponseEntity<Person> editPerson(@RequestBody @Valid PersonDto personDto) {

        Person pInput = personMapperComponet.toEntity(personDto);

        Person pOutput = iPersonService.savePerson(pInput);

        return ResponseEntity.ok(pOutput);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){

        var result = iPersonService.deletePerson(id);

        return ResponseEntity.ok(result);
    }

}
