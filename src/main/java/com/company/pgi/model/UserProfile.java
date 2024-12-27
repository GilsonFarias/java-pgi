package com.company.pgi.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne(mappedBy = "userProfile")
    // private Users users;


    @Nonnull
    @Column(length = 50)
    private String descricao;

    public UserProfile(){};

    public UserProfile(Long id, 
        // Users users, 
        String descricao) {
        this.id = id;
        // this.users = users;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // public Users getUsers() {
    //     return users;
    // }

    // public void setUsers(Users users) {
    //     this.users = users;
    // }
}
