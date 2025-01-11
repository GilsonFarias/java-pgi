package com.company.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pgi.model.PermissionsProfile;
import com.company.pgi.model.UserProfile;

@Repository
public interface  IPermissionsProfileRepository extends JpaRepository<PermissionsProfile, Long> {
    
    List<PermissionsProfile> findByUserProfile(UserProfile userProfile);
}
