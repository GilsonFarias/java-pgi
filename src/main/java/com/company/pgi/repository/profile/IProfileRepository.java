package com.company.pgi.repository.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pgi.model.Company;
import com.company.pgi.model.Profile;

public interface IProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findProfileByCompany(Company company);

    Optional<Profile> findProfileById(Long id);
    
}
