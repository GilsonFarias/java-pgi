package com.company.pgi.model.dto.users;

import com.company.pgi.model.Person;
import com.company.pgi.model.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersSPDto {

    @NotNull(message = "O id é obrigatória")
    private Long id;

    @NotBlank(message = "O e-mail é obrigatório.")
    @jakarta.validation.constraints.Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotBlank(message = "O login é obrigatório.")
    private String login;

    @NotBlank(message = "O tipo de usuário é obrigatório.")
    private String userType;

    @NotNull(message = "O status é obrigatório.")
    private Boolean status;

    @NotNull(message = "O Profile é obrigatório")
    private Profile profile;

    @NotNull(message = "A pessoa é obrigatório")
    private Person person;

    // @NotNull(message = "A empresa é obrigatória")
    // private Company company;

    public UsersSPDto() {
    }

    public UsersSPDto(Long id, String email, String login, String userType, Boolean status,
            Profile profile, Person person
    // , Company company
    ) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.userType = userType;
        this.status = status;
        this.person = person;
        this.profile = profile;
        // this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // public Company getCompany() {
    // return company;
    // }

    // public void setCompany(Company company) {
    // this.company = company;
    // }
}
