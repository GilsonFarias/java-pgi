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
    private Long id;

    @NonNull
    @Column(name = "hierarchy", columnDefinition = "INT COMMENT '1 = Matriz, 2 = Filial'")
    private Integer hierarchy;

    @NonNull
    @Column(length = 60)
    private String name;

    @NonNull
    @Column(length = 60)
    private String businessName;

    private Date dateFuondation;
    
    public Company(){}
    
    public Company(Long id, Integer hierarquia, String name, String businessName, Date dateFuondation) {
        this.id = id;
        this.hierarchy = hierarquia;
        this.name = name;
        this.businessName = businessName;
        this.dateFuondation = dateFuondation;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Date getDateFuondation() {
        return dateFuondation;
    }

    public void setDateFuondation(Date dateFuondation) {
        this.dateFuondation = dateFuondation;
    }

}
