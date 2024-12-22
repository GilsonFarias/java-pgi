package com.company.pgi.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date contract_date;

    private Date contract_end;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public Employee(){}

    public Employee(Date contract_date, Date contract_end, Integer id, Person person) {
        this.contract_date = contract_date;
        this.contract_end = contract_end;
        this.id = id;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getContract_date() {
        return contract_date;
    }

    public void setContract_date(Date contract_date) {
        this.contract_date = contract_date;
    }

    public Date getContract_end() {
        return contract_end;
    }

    public void setContract_end(Date contract_end) {
        this.contract_end = contract_end;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    
}
