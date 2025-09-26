package com.company.pgi.service.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Company;
import com.company.pgi.model.Users;
import com.company.pgi.repository.ICompanyRepository;
import com.company.pgi.service.permissions.IPermissionService;
import com.company.pgi.utils.AuthenticatedUser;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Autowired
    private IPermissionService iPermissionService;

    @Override
    public List<Company> getAllCompany() {
        return iCompanyRepository.findAll();
    }

    @Override
    public List<Company> getCompanyBaseCNPJ(String CNPJ) {
        iPermissionService.ValidPermission("CPY107");

        try {
             Users user = AuthenticatedUser.getAuthenticatedUser();

             List<Company> list = new ArrayList<>();
             
             switch (user.getUserType()) {
                 case "N1" -> list = iCompanyRepository.findAll();
                 case "N2" -> {
                    String baseCNPJ = CNPJ.substring(0, 8);
                    list = iCompanyRepository.findAll() 
                        .stream()
                        .filter(c -> c.getCnpj() != null && c.getCnpj().startsWith(baseCNPJ))
                        .collect(Collectors.toList());
                }
                case "N3", "N4" -> {
                    String baseCNPJ = CNPJ;
                    list = iCompanyRepository.findAll() 
                        .stream()
                        .filter(c -> c.getCnpj() != null && c.getCnpj().startsWith(baseCNPJ))
                        .collect(Collectors.toList());

                }
                default -> throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Company not found.");
             }
             return list;
        } catch (Exception e) {
             throw new ApiCustomException(HttpStatus.NOT_FOUND, "Company not faund.");
        }
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        iPermissionService.ValidPermission("CPY107");


        Optional<Company> company = iCompanyRepository.findById(id);

        company.get().getCnpj();


        return iCompanyRepository.findById(id);
    }

    @Override
    public Company saveCompany(Company company) {
        return iCompanyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        iCompanyRepository.deleteById(id);
    }
}
