package com.company.pgi.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 60, message = "O nome não pode ter mais de 60 caracteres.")
    private String name;

    @NotBlank(message = "O name_responsible é obrigatório.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name_responsible;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private Date date_birth;

    @NotNull(message = "O tipo do documento é obrigatório.")
    private Integer type_doc;
    
    @NotBlank(message = "O campo é obrigatório.")
    private String nro_doc;

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
