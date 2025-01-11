package com.company.pgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pgi.model.Permissions;

@Repository
public interface IPermissionsRepository extends JpaRepository<Permissions, String>{

}
