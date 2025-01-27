package com.company.pgi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.company.pgi.dto.UsersDto;
import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.repository.IUsersRepository;
import com.company.pgi.service.Mapper.IUsersMapper;
import com.company.pgi.service.Mapper.UsersMapperComponet;
import com.company.pgi.service.permissions.IPermissionsService;
import com.company.pgi.service.person.IPersonService;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository iUserRepository;

    @Autowired
    private IPermissionsService iPermissionsService;

    @Autowired
    private IPersonService iPersonService;


    //@Override
    public UsersDto saveUsers(UsersDto userDto) {
        //ResponseBase<UsersDto> responseBase = new ResponseBase<>();
        iPermissionsService.ValidPermission("USE101");

        try {

            var user = IUsersMapper.INSTANCE.toEntity(userDto);

            var person = iPersonService.savePerson(user.getPerson());

            user.setPerson(person);

            var userRest = iUserRepository.save(user);

            userDto = IUsersMapper.INSTANCE.toDto(userRest);

            userDto.setPassword("***");

            return userDto;

            // responseBase.setData(List.of(userDto));

            // return responseBase;
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "User not faund.");
        }

    }
    
    public ResponseBase<UsersDto> findAll() {
        ResponseBase<UsersDto> responseBase = new ResponseBase<>();
        iPermissionsService.ValidPermission("USE105");

        var users = iUserRepository.findAll();
        if(users.isEmpty()){
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
        }

        var userDto = IUsersMapper.INSTANCE.toDtoList(users);

        responseBase.setData(userDto);
        responseBase.setStatusCode(HttpStatus.OK);
        responseBase.setMessage("ok");

        return responseBase;
    }

    public List<UsersDto> findAll2() {
        iPermissionsService.ValidPermission("USE105");
        try {

            var users = iUserRepository.findAll();
            if (users.isEmpty()) {
                throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
            }

            var usersDto = IUsersMapper.INSTANCE.toDtoList(users);

            for (UsersDto userDto : usersDto) {
                userDto.setPassword("***");
            }

            return usersDto;

        } catch (ApiCustomException e) {
            throw new ApiCustomException(e.getStatus(), e.getMessage());
        }
    }

    public UsersDto findById(Long id){
        iPermissionsService.ValidPermission("PEN104");
        
        var user =  iUserRepository.findById(id);
        
        if (user.isEmpty()) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
        }

        UsersMapperComponet map = new UsersMapperComponet();

        var userDto = map.toDtoSPass(user.get());

        return userDto;
    }

}
