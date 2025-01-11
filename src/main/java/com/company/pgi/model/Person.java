package com.company.pgi.model;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(length = 60)
    private String name;

    @Column(length = 60)
    private String name_responsible;

    private Date date_birth;

    private Integer type_doc;
    
    @Column(length = 15)
    private String nro_doc;


    public Person(){}
    
    // Contructor
    public Person(Date date_birth, Long id, String name, String name_responsible,
            String nro_doc, Integer type_doc, Users users) {
        this.date_birth = date_birth;
        this.id = id;
        this.name = name;
        this.name_responsible = name_responsible;
        this.nro_doc = nro_doc;
        this.type_doc = type_doc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_responsible() {
        return name_responsible;
    }

    public void setName_responsible(String name_responsible) {
        this.name_responsible = name_responsible;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public Integer getType_doc() {
        return type_doc;
    }

    public void setType_doc(Integer type_doc) {
        this.type_doc = type_doc;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }
}
