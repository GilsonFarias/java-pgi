package com.company.pgi.model;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(length = 60)
    private String name;

    @NonNull
    @Column(length = 60)
    private String business_name;

    private Date date_fuoudation;

    public Company(){}

    public Company(String business_name, Date date_fuoudation, Integer id, String name) {
        this.business_name = business_name;
        this.date_fuoudation = date_fuoudation;
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public Date getDate_fuoudation() {
        return date_fuoudation;
    }

    public void setDate_fuoudation(Date date_fuoudation) {
        this.date_fuoudation = date_fuoudation;
    }
}
