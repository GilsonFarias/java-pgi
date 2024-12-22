package com.company.pgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pgi.model.Users;

public interface IUsersRepository extends JpaRepository<Users, Long> {

    Users findByLogin(String login);
    
}
