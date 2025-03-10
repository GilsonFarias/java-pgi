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
public class Users implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 60)
    private String email;
    
    // @NonNull
    // @Column(unique = true, nullable = false, length = 40)
    // private String login;
    
    //@NonNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public Users(){}

    public Users(Long id, String email, /*String login,*/ String password,
                UserProfile userProfile, Person person, Company company
    ) {
        this.id = id;
        this.email = email;
        // this.login = login;
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
    // public String getLogin() {
    //     return login;
    // }
    // public void setLogin(String login) {
    //     this.login = login;
    // }
    
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserProfile getUserProfile(){
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfiles){
        this.userProfile = userProfiles;
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority( getUserProfile().getProfile()));
    }

    @Override
    public String getUsername() {
        return email;
    }

        // Interfaces para grupos de validação
    public interface OnCreate extends Default {}
    public interface OnUpdate extends Default {}

}