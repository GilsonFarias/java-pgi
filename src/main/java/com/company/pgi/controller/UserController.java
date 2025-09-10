package com.company.pgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.dto.LoginDto;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.model.dto.users.UsersAccountDto;
import com.company.pgi.model.dto.users.UsersDto;
import com.company.pgi.model.dto.users.UsersSPDto;
import com.company.pgi.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UsersSPDto> getUserById(@PathVariable Long id) {
        var userSPDto = userService.findById(id);

        return ResponseEntity.ok(userSPDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsersSPDto>> getAllUserList() {

        var userSPDto = userService.userList();

        if (!userSPDto.isEmpty()) {
            return ResponseEntity.ok(userSPDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<UsersSPDto> userInsert(@RequestBody @Validated(UsersDto.OnCreate.class) UsersDto usersDto) {

        var dto = userService.userInsert(usersDto);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/edit")
    public ResponseEntity<UsersSPDto> userEditI(
            @RequestBody @Validated(UsersDto.OnUpdate.class) UsersSPDto usersSPDto) {

        var dto = userService.userEdit(usersSPDto);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/editPassword")
    public ResponseEntity<ResponseBase<String>> userEditPasswordI(@RequestBody LoginDto loginDto) {

        ResponseBase<String> rsp = new ResponseBase<>();

        rsp.setMessage(userService.userEditPassword(loginDto));

        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/insertUserAccount")
    public ResponseEntity<String> postMethodName(@RequestBody UsersAccountDto usersAccountDto) {

        userService.createUserAccount(usersAccountDto);

        return ResponseEntity.ok("Seu cadastro foi concluído");
    }

}
