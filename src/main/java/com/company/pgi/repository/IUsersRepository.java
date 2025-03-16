package com.company.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.pgi.model.Company;
import com.company.pgi.model.Person;
import com.company.pgi.model.Profile;
import com.company.pgi.model.Users;

import jakarta.transaction.Transactional;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {

        Users findByEmail(String email);;

        List<Users> findByProfile_Company(Company company);

        @Modifying
        @Transactional
        @Query("UPDATE Users u SET u.email = :email, u.login = :login, u.userType = :userType, " +
                        "u.profile = :profile, u.status = :status, u.person = :person " +
                        "WHERE u.id = :id")
        int updateUser(@Param("id") Long id,
                        @Param("email") String email,
                        @Param("login") String login,
                        @Param("userType") String userType,
                        @Param("status") Boolean status,
                        @Param("profile") Profile profile,
                        @Param("person") Person person);

        @Modifying
        @Transactional
        @Query("UPDATE Users u SET u.password = :password WHERE u.email = :email")
        void changePassword(@Param("password") String password, @Param("email") String email);

}