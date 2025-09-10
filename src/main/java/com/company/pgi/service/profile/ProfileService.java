package com.company.pgi.service.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Company;
import com.company.pgi.model.Profile;
import com.company.pgi.model.Users;
import com.company.pgi.repository.ICompanyRepository;
import com.company.pgi.repository.profile.IProfileRepository;
import com.company.pgi.service.permissions.IPermissionService;
import com.company.pgi.utils.AuthenticatedUser;

import lombok.experimental.var;

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

        Users user = AuthenticatedUser.getAuthenticatedUser();

        if(profile.getCompany().getId() == user.getProfile().getCompany().getId()){
            var profileResult = iProfileRepository.save(profile);
            return profileResult;
        } else {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Você não pode criar um perfil para outra empresa.");
        }
    }

    @Override
    public List<Profile> getProfilesList() {
        iPermissionService.ValidPermission("UPF105");

        try {
            Users user = AuthenticatedUser.getAuthenticatedUser();

            List<Profile> list = new ArrayList<>();
            
            switch (user.getUserType()) {
                case "N1" -> list = iProfileRepository.findAll();
                case "N2", "N3", "N4" -> {
                    Company company = user.getProfile().getCompany();
                    list = iProfileRepository.findProfileByCompany(company);
                }
            default -> throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Profile not found.");
            }
            return list;
            
        } catch (Exception e) {
             throw new ApiCustomException(HttpStatus.NOT_FOUND, "User not faund.");
        }

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
