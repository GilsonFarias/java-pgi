package com.company.pgi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.pgi.model.Users;
import com.company.pgi.repository.IUsersRepository;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository iUserRepository;
    private Authentication authentication;
    
    public List<Users> findAll() {
        authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            authentication.getName(); // Nome do usuário autenticado
        }

        return iUserRepository.findAll();
    }

    // public List<UsersList> findListUser() {
    //     return iUserRepository.findList();
    // }

    //??????
    // public String getLoggedInUsername() {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     if (authentication != null && authentication.isAuthenticated()) {
    //         return authentication.getName(); // Nome do usuário autenticado
    //     }
    //     return null;
    // }
}
