package com.company.pgi.service.Mapper;

import org.springframework.stereotype.Component;

import com.company.pgi.model.Users;
import com.company.pgi.model.dto.users.UsersDto;

@Component
public class UsersMapperComponet {

    public Users toEntity(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());

        return users;
    }

    public UsersDto toDto(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setEmail(users.getEmail());
        // usersDto.setLogin(users.getLogin());
        usersDto.setPassword(users.getPassword());
        usersDto.setProfile(users.getProfile());
        usersDto.setPerson(users.getPerson());
        // usersDto.setCompany(users.getCompany());

        return usersDto;
    }

    public UsersDto toDtoSPass(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setEmail(users.getEmail());
        // usersDto.setLogin(users.getLogin());
        // usersDto.setPassword(users.getPassword());
        usersDto.setProfile(users.getProfile());
        usersDto.setPerson(users.getPerson());
        // usersDto.setCompany(users.getCompany());

        return usersDto;
    }

}
