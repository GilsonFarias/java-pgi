package com.company.pgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pgi.model.Users;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    //Users findByLoginPass(String login, String password );
}