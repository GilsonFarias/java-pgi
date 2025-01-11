package com.company.pgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pgi.model.Company;

public interface ICompanyRepository extends JpaRepository<Company, Long> {

}
