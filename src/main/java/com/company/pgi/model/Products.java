package com.company.pgi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String embalagem;

    // Constructor
    public Products() {}

    public Products(String name, String embalagem) {
        this.name = name;
        this.embalagem = embalagem;
    }

    // Getters e Setters
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
    public String getEmbalagem() {
        return embalagem;
    }
    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }
}
