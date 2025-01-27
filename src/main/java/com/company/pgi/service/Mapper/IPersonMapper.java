package com.company.pgi.service.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.company.pgi.model.Person;
import com.company.pgi.model.dto.PersonDto;

@Mapper
public interface IPersonMapper {

    IPersonMapper INSTANCE = Mappers.getMapper(IPersonMapper.class);

    Person toPerson(PersonDto personDto);

    PersonDto toDto(Person person);

}
