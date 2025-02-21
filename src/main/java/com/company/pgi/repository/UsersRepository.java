package com.company.pgi.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.company.pgi.dto.LoginDto;
import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Company;
import com.company.pgi.model.Person;
import com.company.pgi.model.UserProfile;
import com.company.pgi.model.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UsersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IUsersRepository iUserRepository;


    @Transactional
    public Optional<Users> upDateUsers( 
        Long id, String email, 
        String login, UserProfile userProfile, 
        Person person, Company company) {

        entityManager.createQuery("UPDATE Users u SET u.email = :email, u.login = :login,"
                                                    + "u.userProfile = :userProfile, "
                                                    + "u.person = :person, u.company = :company " 
                                                    + "WHERE u.id = :id")
            .setParameter("email", email)
            .setParameter("login", login)
            .setParameter("userProfile", userProfile)
            .setParameter("person", person)
            .setParameter("company", company)
            .setParameter("id", id)
            .executeUpdate();

            return iUserRepository.findById(id);

    }

    @Transactional
    public String changePassword( LoginDto loginDto ){
        try {
            entityManager.createQuery("UPDATE Users u SET u.password = :password WHERE u.email = :email")
            .setParameter("email", loginDto.login())
            .setParameter("password", loginDto.password())
            .executeUpdate();
    
            return "A senha foi alterada.";
            
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
