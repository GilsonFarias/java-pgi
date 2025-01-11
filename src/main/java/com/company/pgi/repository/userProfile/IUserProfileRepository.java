package com.company.pgi.repository.userProfile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pgi.model.UserProfile;

public interface IUserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
