package com.company.pgi.dto;

import com.company.pgi.model.Company;
import com.company.pgi.model.Person;
import com.company.pgi.model.UserProfile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersDto {

    private Long id;

    @NotBlank(message = "O e-mail é obrigatório.")
    @jakarta.validation.constraints.Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotBlank(message = "O login é obrigatório.")
    private String login;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    @NotNull(message = "O Profile é obrigatório")
    private UserProfile userProfile;

    @NotNull(message="A pessoa é obrigatório")
    private Person person;
    
    @NotNull(message="A empresa é obrigatória")
    private Company company;

    
    public UsersDto() {
    }

    public UsersDto(Long id, String email, String login, String password, UserProfile userProfile, Person person, Company company) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.userProfile = userProfile;
        this.person = person;
        this.company = company;
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

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Método estático para converter a entidade Users em UsersDTO
    // public static UsersDto fromEntity(Users user) {
    //     return new UsersDto(
    //         user.getId(),
    //         user.getEmail(),
    //         user.getLogin()
    //     );
    // }

    public String getPassword() {
        return password;
    }

}
