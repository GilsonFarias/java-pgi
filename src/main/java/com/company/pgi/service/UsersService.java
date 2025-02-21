package com.company.pgi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.pgi.dto.LoginDto;
import com.company.pgi.dto.UsersDto;
import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.dto.users.UsersSPDto;
import com.company.pgi.repository.IUsersRepository;
import com.company.pgi.repository.UsersRepository;
import com.company.pgi.service.Mapper.IUsersMapper;
import com.company.pgi.service.permissions.IPermissionService;
import com.company.pgi.service.person.IPersonService;
import com.company.pgi.utils.PasswordValidator;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository iUserRepository;

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IPersonService iPersonService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@Override
    public UsersSPDto userInsert(UsersDto userDto) {
        iPermissionService.ValidPermission("USE101");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try {
            var user = IUsersMapper.INSTANCE.toEntity(userDto);

            if(this.iUserRepository.findByEmail(user.getEmail()) != null){
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado.");
            }

            if(!PasswordValidator.isValidPassword(user.getPassword()))
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Invalid password");
            
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            var person = iPersonService.savePerson(user.getPerson());

            user.setPerson(person);

            var userRest = iUserRepository.save(user);

            var userDtoResult = IUsersMapper.INSTANCE.toSPDto(userRest);

            return userDtoResult;

        } catch (ApiCustomException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Error: Não foi possível executar a instrução.");
        }
    }

    public UsersSPDto userEdit(UsersSPDto userSPDto) {
        iPermissionService.ValidPermission("USE102");

        try {
            var user = IUsersMapper.INSTANCE.toEntitySP(userSPDto);

            var person = iPersonService.savePerson(user.getPerson());

            user.setPerson(person);

            var userRest = usersRepository.upDateUsers(user.getId(), user.getEmail(),
                    user.getEmail(), user.getUserProfile(), user.getPerson(), user.getCompany());

            var userSPDtoResult = IUsersMapper.INSTANCE.toSPDto(userRest.get());

            return userSPDtoResult;

        } catch (ApiCustomException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Error: Não foi possível executar a instrução.");
        }
    }

    public List<UsersSPDto> userList() {
        iPermissionService.ValidPermission("USE105");
        try {

            var users = iUserRepository.findAll();
            if (users.isEmpty()) {
                throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
            }

            var usersSPDto = IUsersMapper.INSTANCE.toSPDtoList(users);

            return usersSPDto;

        } catch (ApiCustomException e) {
            throw new ApiCustomException(e.getStatus(), e.getMessage());
        }
    }

    public UsersSPDto findById(Long id){
        iPermissionService.ValidPermission("PEN104");
        
        var user =  iUserRepository.findById(id);
        
        if (user.isEmpty()) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
        }

        var userSPDto = IUsersMapper.INSTANCE.toSPDto(user.get()); //usersMapperComponet.toDtoSPass(user.get());

        return userSPDto;
    }

    public String userEditPassword( LoginDto loginDto ){
        iPermissionService.ValidPermission("USE106");

        try {

            var userExists = iUserRepository.findByEmail(loginDto.login());

            if (userExists == null)
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Usuário informado não existe.");

            if(!PasswordValidator.isValidPassword(loginDto.password()))
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Invalid password");

            LoginDto lDto = new LoginDto(loginDto.login(), bCryptPasswordEncoder.encode(loginDto.password()));

            return usersRepository.changePassword(lDto);
        } catch (ApiCustomException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Error: Não foi possivel alterar a senha.");
        }
    }
}
