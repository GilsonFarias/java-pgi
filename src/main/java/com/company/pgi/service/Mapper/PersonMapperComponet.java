package com.company.pgi.service.Mapper;

import org.springframework.stereotype.Component;

import com.company.pgi.model.Person;
import com.company.pgi.model.dto.PersonDto;

@Component
public class PersonMapperComponet {

        public Person toEntity(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setName_responsible(personDto.getName_responsible());
        person.setDate_birth(personDto.getDate_birth());
        person.setType_doc(personDto.getType_doc());
        person.setNro_doc(personDto.getNro_doc());
        return person;
    }

    public PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setName_responsible(person.getName_responsible());
        personDto.setDate_birth(person.getDate_birth());
        personDto.setType_doc(person.getType_doc());
        personDto.setNro_doc(person.getNro_doc());
        return personDto;
    }

}
