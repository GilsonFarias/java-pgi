package com.company.pgi.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.groups.Default;

@Entity(name = "Users")
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 60)
    private String email;

    @Column(name = "login", nullable = false, length = 30)
    private String login;

    // @NonNull
    private String password;

    @Column(name = "user_type", nullable = false, length = 8)
    private String userType;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    // @ManyToOne
    // @JoinColumn(name = "company_id", referencedColumnName = "id")
    // private Company company;

    public Users() {
    }

    public Users(Long id, String email, String password, String login,
            String userType, Boolean status, Profile profile, Person person
    // , Company company
    ) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.status = status;
        this.userType = userType;
        this.profile = profile;
        this.person = person;
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // public Company getCompany() {
    // return company;
    // }

    // public void setCompany(Company company) {
    // this.company = company;
    // }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getProfile().getProfile()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Interfaces para grupos de validação
    public interface OnCreate extends Default {
    }

    public interface OnUpdate extends Default {
    }

}
