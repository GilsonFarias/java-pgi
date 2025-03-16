package com.company.pgi.service.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Company;
import com.company.pgi.model.Profile;
import com.company.pgi.repository.ICompanyRepository;
import com.company.pgi.repository.profile.IProfileRepository;
import com.company.pgi.service.permissions.IPermissionService;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IProfileRepository iProfileRepository;

    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Override
    public Profile saveProfile(Profile profile) {
        iPermissionService.ValidPermission("UPF101");

        var profileResult = iProfileRepository.save(profile);

        return profileResult;
    }

    @Override
    public List<Profile> getProfilesList() {
        iPermissionService.ValidPermission("UPF105");

        var list = iProfileRepository.findAll();

        return list;
    }

    @Override
    public Profile getProfileById(Long id) {

        iPermissionService.ValidPermission("UPF104");

        Profile profile = iProfileRepository.findProfileById(id)
                .orElseThrow(() -> new ApiCustomException(HttpStatus.ACCEPTED, "Perfil não encontrado"));

        return profile;
    }

    @Override
    public List<Profile> getProfilesListByCompany(Long idCompany) {

        iPermissionService.ValidPermission("UPF105");

        Company company = iCompanyRepository.findById(idCompany)
                .orElseThrow(() -> new ApiCustomException(HttpStatus.BAD_REQUEST,
                        "Empresa não encontrada para o ID: " + idCompany));

        return iProfileRepository.findProfileByCompany(company);
    }

}
