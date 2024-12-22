package com.company.pgi.service.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pgi.model.Company;
import com.company.pgi.repository.ICompanyRepository;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Override
    public List<Company> getAllCompany() {
        return iCompanyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(Integer id) {
        return iCompanyRepository.findById(id);
    }

    @Override
    public Company saveCompany(Company company) {
        return iCompanyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        iCompanyRepository.deleteById(id);
    }
}
