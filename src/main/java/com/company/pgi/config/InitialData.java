package com.company.pgi.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.pgi.model.Company;
import com.company.pgi.model.Person;
import com.company.pgi.model.UserProfile;
import com.company.pgi.model.Users;
import com.company.pgi.repository.ICompanyRepository;
import com.company.pgi.repository.IUsersRepository;
import com.company.pgi.repository.person.IPersonRepository;
import com.company.pgi.repository.userProfile.IUserProfileRepository;

@Component
public class InitialData implements CommandLineRunner {

    private final IUsersRepository iUsersRepository;
    private final IUserProfileRepository iUserProfileRepository;
    private final ICompanyRepository iCompanyRepository;
    private final IPersonRepository iPersonRepository;

    public InitialData(IUsersRepository iUsersRepository,
            IUserProfileRepository iUserProfileRepository,
            ICompanyRepository iCompanyRepository,
            IPersonRepository iPersonRepository) {
        this.iUsersRepository = iUsersRepository;
        this.iUserProfileRepository = iUserProfileRepository;
        this.iCompanyRepository = iCompanyRepository;
        this.iPersonRepository = iPersonRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (iUsersRepository.count() == 0) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String dataHoraString = "2024-12-27T14:30:00";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Person person = new Person();
            person.setDate_birth(formatter.parse(dataHoraString));
            person.setName("Gilson Farias");
            person.setName_responsible("Gilson Belem");
            person.setNro_doc("11122233345");
            person.setType_doc(1);
            var regPerson = iPersonRepository.save(person);

            Company company = new Company();
            company.setBusiness_name("Razão Social");
            company.setDate_fuoudation(formatter.parse(dataHoraString));
            company.setName("Noma Fantazia");
            var regCompany = iCompanyRepository.save(company);

            UserProfile userProfile = new UserProfile();
            userProfile.setProfile("Administrador");
            var regUserProfile = iUserProfileRepository.save(userProfile);

            Users userAdm = new Users();
            userAdm.setEmail("gilson@Gmail.com");
            userAdm.setLogin("Gilson");
            userAdm.setPassword(passwordEncoder.encode("123456"));
            userAdm.setPerson(regPerson);
            userAdm.setCompany(regCompany);
            userAdm.setUserProfile(regUserProfile);
            iUsersRepository.save(userAdm);

            System.out.println("Dados iniciais cadastrados.");
        }
    }
}
