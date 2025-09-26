package com.company.pgi.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Profile;

@Service
public interface IProfileService {

    Profile saveProfile(Profile profile);

    List<Profile> getProfilesList();

    List<Profile> getProfilesListByCompany(Long id_company);

    Profile getProfileById(Long id);

    //String deleteProfile(Long id);

    ApiCustomException deleteProfile(Long id);
}
