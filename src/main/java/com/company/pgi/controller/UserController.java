package com.company.pgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Users;
import com.company.pgi.service.UsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UsersService userService;

    @GetMapping("/list")
    public List<Users> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/salve")
    public String postSalve(@RequestBody Users users) {       
        return "Salvouuuuu";
    }
}
