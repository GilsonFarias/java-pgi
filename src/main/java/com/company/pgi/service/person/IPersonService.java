package com.company.pgi.service.person;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.pgi.dto.RequestBase;
import com.company.pgi.model.Person;
import com.company.pgi.model.dto.ResponseBase;

@Service
public interface  IPersonService {
    @ResponseBody
    Optional<Person> getPersonById(Long id);
    ResponseBase<Person> savePerson(Person person);
    void deletePerson (Long id);

    ResponseBase<Person> getPresonList(RequestBase<Person> requestBase);
}
