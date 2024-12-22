package com.company.pgi.controller.company;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.company.pgi.model.Company;

public interface ICompanyController {
    List<Company> getAllCompany();
    ResponseEntity<Company> getCompanyById(Integer id);
    Company createCompany(Company company);
    ResponseEntity<Company> updateCompany(Integer id, Company company);
    ResponseEntity<Void> deleteCompany(Integer id);
}
