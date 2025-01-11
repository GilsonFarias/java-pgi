package com.company.pgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Users;
import com.company.pgi.service.UsersService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersService userService;

    @GetMapping("/list")
    public List<Users> getAllUsers() {
        return userService.findAll();
    }

    // @GetMapping("/list2")
    // public List<UsersList> getUserList() {
    //     return userService.findListUser();
    // }

    @PostMapping("/salve")
    public String postSalve(@RequestBody Users users) {       
        return "Salvouuuuu";
    }
}
