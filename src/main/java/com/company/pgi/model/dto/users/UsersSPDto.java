package com.company.pgi.model.dto.users;

import com.company.pgi.model.Company;
import com.company.pgi.model.Person;
import com.company.pgi.model.UserProfile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersSPDto {

    @NotNull(message = "O id é obrigatória")
    private Long id;

    @NotBlank(message = "O e-mail é obrigatório.")
    @jakarta.validation.constraints.Email(message = "O e-mail deve ser válido.")
    private String email;

    // @NotBlank(message = "O login é obrigatório.")
    // private String login;

    @NotNull(message = "O Profile é obrigatório")
    private UserProfile userProfile;

    @NotNull(message="A pessoa é obrigatório")
    private Person person;
    
    @NotNull(message="A empresa é obrigatória")
    private Company company;

    public  UsersSPDto(){}

    public UsersSPDto(Company company, String email, Long id, /*String login,*/ Person person, UserProfile userProfile) {
        this.company = company;
        this.email = email;
        this.id = id;
        //this.login = login;
        this.person = person;
        this.userProfile = userProfile;
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

    // public String getLogin() {
    //     return login;
    // }

    // public void setLogin(String login) {
    //     this.login = login;
    // }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
