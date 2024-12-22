package com.company.pgi.service.Company;

import java.util.List;
import java.util.Optional;

import com.company.pgi.model.Company;

public interface ICompanyService {
    List<Company> getAllCompany();
    Optional<Company> getCompanyById(Integer id);
    Company saveCompany(Company company);
    void deleteCompany (Integer id);
}
