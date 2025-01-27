package com.company.pgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.dto.UsersDto;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.service.UsersService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getMethodName(@PathVariable Long id) {
        var userDto = userService.findById(id);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/list")
    public ResponseBase<UsersDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/list2")
    public ResponseEntity<List<UsersDto>> getAllUsers2() {

        var userDto = userService.findAll2();

        if (!userDto.isEmpty()) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<UsersDto> postSalve(@RequestBody @Valid UsersDto usersDto) {   

        var dto = userService.saveUsers(usersDto);

        return ResponseEntity.ok(dto);
    }
    
}
