package com.company.pgi.service.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.company.pgi.dto.UsersDto;
import com.company.pgi.model.Users;

@Mapper
public interface IUsersMapper {

    IUsersMapper INSTANCE = Mappers.getMapper(IUsersMapper.class);

    Users toEntity(UsersDto usersDto);

    List<Users> toEntityList(List<UsersDto> usersDto);

    UsersDto toDto(Users users);

    List<UsersDto> toDtoList(List<Users> users);
}
