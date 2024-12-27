package com.company.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.pgi.model.Users;
import com.company.pgi.projection.users.UsersList;

public interface IUsersRepository extends JpaRepository<Users, Long> {

    Users findByLogin(String login);

    //@Query("SELECT u.login AS login, u.email AS email FROM users u")
    @Query(value = "CALL Get_User_SP()", nativeQuery = true)
    List<UsersList> findList();

}