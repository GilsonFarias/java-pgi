package com.company.pgi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pgi.model.Users;
import com.company.pgi.repository.IUsersRepository;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository iUserRepository;

    public List<Users> findAll() {
        return iUserRepository.findAll();
    }
}
