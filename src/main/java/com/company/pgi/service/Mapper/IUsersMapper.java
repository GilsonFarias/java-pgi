package com.company.pgi.service.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.company.pgi.dto.UsersDto;
import com.company.pgi.model.Users;
import com.company.pgi.model.dto.users.UsersSPDto;

@Mapper
public interface IUsersMapper {

    IUsersMapper INSTANCE = Mappers.getMapper(IUsersMapper.class);

    @Mapping(target = "authorities", ignore = true)
    Users toEntity(UsersDto usersDto);

    List<Users> toEntityList(List<UsersDto> usersDto);

    UsersDto toDto(Users users);

    List<UsersDto> toDtoList(List<Users> users);

    //Ignore password

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Users toEntitySP(UsersSPDto usersSPDto);

    UsersSPDto toSPDto(Users users);

    List<UsersSPDto> toSPDtoList(List<Users> users);

}
