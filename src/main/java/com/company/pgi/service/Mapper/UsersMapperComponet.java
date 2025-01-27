package com.company.pgi.service.Mapper;

import com.company.pgi.dto.UsersDto;
import com.company.pgi.model.Users;

public class UsersMapperComponet {

    public Users toEntity( UsersDto usersDto){
        Users users = new Users();

        return users;
    }

    public UsersDto toDto (Users users){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setEmail(users.getEmail());
        usersDto.setLogin(users.getLogin());
        usersDto.setPassword(users.getPassword());
        usersDto.setUserProfile(users.getUserProfile());
        usersDto.setPerson(users.getPerson());
        usersDto.setCompany(users.getCompany());

        return usersDto;
    }

    public UsersDto toDtoSPass (Users users){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setEmail(users.getEmail());
        usersDto.setLogin(users.getLogin());
        //usersDto.setPassword(users.getPassword());
        usersDto.setUserProfile(users.getUserProfile());
        usersDto.setPerson(users.getPerson());
        usersDto.setCompany(users.getCompany());

        return usersDto;
    }

}
