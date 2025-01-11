package com.company.pgi.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pgi.model.Person;

public interface IPersonRepository extends JpaRepository<Person, Long> {

}
