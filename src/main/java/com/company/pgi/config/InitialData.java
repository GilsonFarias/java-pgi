package com.company.pgi.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.pgi.model.Company;
import com.company.pgi.model.Person;
import com.company.pgi.model.Profile;
import com.company.pgi.model.Users;
import com.company.pgi.repository.ICompanyRepository;
import com.company.pgi.repository.IUsersRepository;
import com.company.pgi.repository.person.IPersonRepository;
import com.company.pgi.repository.profile.IProfileRepository;
import com.company.pgi.service.permissions.IPermissionService;

@Component
public class InitialData implements CommandLineRunner {

    private final IUsersRepository iUsersRepository;
    private final IProfileRepository iProfileRepository;
    private final ICompanyRepository iCompanyRepository;
    private final IPersonRepository iPersonRepository;
    private final IPermissionService iPermissionService;

    public InitialData(IUsersRepository iUsersRepository,
            IProfileRepository iProfileRepository,
            ICompanyRepository iCompanyRepository,
            IPersonRepository iPersonRepository,
            IPermissionService iPermissionService) {
        this.iUsersRepository = iUsersRepository;
        this.iProfileRepository = iProfileRepository;
        this.iCompanyRepository = iCompanyRepository;
        this.iPersonRepository = iPersonRepository;
        this.iPermissionService = iPermissionService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (iUsersRepository.count() == 0) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Person person = new Person();
            person.setDate_birth(formatter.parse("2024-12-27T09:00:00"));
            person.setName("Gilson Farias");
            person.setName_responsible("Gilson Belem");
            person.setNro_doc("11122233345");
            person.setType_doc(1); // 1 = CPF, 2 = RG, 3 = Outros
            var regPerson = iPersonRepository.save(person);

            Company company = new Company();
            company.setHierarchy(1);// 1 = Matriz, 2 = Filial
            company.setCnpj("00623904000173");
            company.setName("Noma Fantazia");
            company.setBusinessName("Razão Social");
            company.setDateFuondation(formatter.parse("2020-01-15T10:00:00"));
            iCompanyRepository.save(company);

            Profile profile = new Profile();
            profile.setProfile("Administrador");
            profile.setCompany(company);
            var regProfile = iProfileRepository.save(profile);

            Users userAdm = new Users();
            userAdm.setEmail("gilson@gmail.com");
            userAdm.setLogin("gilson@gmail.com");
            userAdm.setUserType("N1");
            userAdm.setPassword(passwordEncoder.encode("Gilson#99"));
            userAdm.setStatus(true);
            userAdm.setPerson(regPerson);
            userAdm.setProfile(regProfile);
            iUsersRepository.save(userAdm);
            
            iPermissionService.updatePermissionsList();
            
            iPermissionService.updateProfiles(1L);

            System.out.println("Dados iniciais cadastrados.");
        }
    }
}
