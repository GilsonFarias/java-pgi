package com.company.pgi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Users;
import com.company.pgi.model.dto.LoginDto;
import com.company.pgi.model.dto.users.UsersAccountDto;
import com.company.pgi.model.dto.users.UsersDto;
import com.company.pgi.model.dto.users.UsersSPDto;
import com.company.pgi.repository.ICompanyRepository;
import com.company.pgi.repository.IUsersRepository;
import com.company.pgi.repository.person.IPersonRepository;
import com.company.pgi.repository.profile.IProfileRepository;
import com.company.pgi.service.Mapper.IUsersMapper;
import com.company.pgi.service.Mapper.UsersMapperComponet;
import com.company.pgi.service.permissions.IPermissionService;
import com.company.pgi.service.person.IPersonService;
import com.company.pgi.utils.CreateSchema;
import com.company.pgi.utils.AuthenticatedUser;
import com.company.pgi.utils.PasswordValidator;

import jakarta.transaction.Transactional;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository iUserRepository;

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IPersonService iPersonService;

    @Autowired
    private IUsersRepository iUsersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Autowired
    private IPersonRepository iPersonRepository;

    @Autowired
    private IProfileRepository iProfileRepository;

    @Autowired
    private CreateSchema createSchema;

    @Autowired
    private UsersMapperComponet usersMapperComponet;

    // @Override
    @Transactional
    public UsersSPDto userInsert(UsersDto userDto) {
        iPermissionService.ValidPermission("USE101");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try {
            var user = IUsersMapper.INSTANCE.toEntity(userDto);

            if (this.iUserRepository.findByEmail(user.getEmail()) != null) {
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado.");
            }

            if (!PasswordValidator.isValidPassword(user.getPassword()))
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

    public List<UsersSPDto> userList() {
        iPermissionService.ValidPermission("USE105");
        try {
            Users user = AuthenticatedUser.getAuthenticatedUser();

            List<Users> userList = new ArrayList<>();

            switch (user.getUserType()) {
                case "N1" -> userList = iUserRepository.findAll();
                case "N2", "N3", "N4" -> {
                    var company = user.getProfile().getCompany();
                    userList = iUserRepository.findByProfile_Company(company);
                }
                default -> throw new ApiCustomException(HttpStatus.NOT_ACCEPTABLE, "User not faund.");
            }

            if (userList.isEmpty()) {
                throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
            }

            var usersSPDto = IUsersMapper.INSTANCE.toSPDtoList(userList);

            return usersSPDto;

        } catch (ApiCustomException e) {
            throw new ApiCustomException(e.getStatus(), e.getMessage());
        }
    }

    public UsersSPDto findById(Long id) {
        iPermissionService.ValidPermission("PEN104");

        var user = iUserRepository.findById(id);

        if (user.isEmpty()) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
        }

        var userSPDto = IUsersMapper.INSTANCE.toSPDto(user.get()); // usersMapperComponet.toDtoSPass(user.get());

        return userSPDto;
    }

    public UsersSPDto userEdit(UsersSPDto userSPDto) {
        iPermissionService.ValidPermission("USE102");

        try {
            var user = IUsersMapper.INSTANCE.toEntitySP(userSPDto);

            var person = iPersonService.savePerson(user.getPerson());

            user.setPerson(person);

            int updated = iUsersRepository.updateUser(user.getId(), user.getEmail(), user.getLogin(),
                    user.getUserType(), user.getStatus(), user.getProfile(), user.getPerson());

            if (updated > 0) {
                Users userRest = iUserRepository.findById(user.getId())
                        .orElseThrow(() -> new ApiCustomException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

                return IUsersMapper.INSTANCE.toSPDto(userRest);
            } else {

                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Falha na atualização.");
            }
        } catch (ApiCustomException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Error: Não foi possível executar a instrução.");
        }
    }

    public String userEditPassword(LoginDto loginDto) {
        iPermissionService.ValidPermission("USE106");

        try {

            var userExists = iUserRepository.findByEmail(loginDto.login());

            if (userExists == null)
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Usuário informado não existe.");

            if (!PasswordValidator.isValidPassword(loginDto.password()))
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Invalid password");

            iUsersRepository.changePassword(bCryptPasswordEncoder.encode(loginDto.password()), loginDto.login());

            return "Senha alterada";

        } catch (ApiCustomException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Error: Não foi possivel alterar a senha.");
        }
    }

    @Transactional
    public String createUserAccount(UsersAccountDto usersAccountDto) {
        try {

            Users user = IUsersMapper.INSTANCE.toEntity(usersAccountDto.getUsersDto());

            if (!PasswordValidator.isValidPassword(user.getPassword()))
                throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Invalid password");

            var company = iCompanyRepository.save(user.getProfile().getCompany());
            
            var person = iPersonRepository.save(user.getPerson());
            
            user.getProfile().setCompany(company);
            
            var profile = iProfileRepository.save(user.getProfile());
            
            user.setProfile(profile);
            
            user.setPerson(person);
            
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            
            iUsersRepository.save(user);
            
            createSchema.addSquema(usersAccountDto.getModelSystem() + "_" + company.getCnpj());

            return "Usuário criado com sucesso";

        } catch (ApiCustomException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Não foi possível criar a conta");
        }
    }

}
