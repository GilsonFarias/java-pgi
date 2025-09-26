package com.company.pgi.controller.company;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Company;
import com.company.pgi.service.Company.ICompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController{
    @Autowired
    private ICompanyService iCompanyService;

    @GetMapping("/list")
    public List<Company> getAllCompany() {
        return iCompanyService.getAllCompany();
    }

    @PutMapping("/list2")
    public List<Company> getCompanyBaseCNPJ(@RequestBody Map<String, String> requestBody) {
        String CNPJ = requestBody.get("baseCNPJ");
        return iCompanyService.getCompanyBaseCNPJ(CNPJ);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = iCompanyService.getCompanyById(id);
        return company.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); 
    }


    @PutMapping("/{id}")
    public Company createCompany(Company company) {
        return iCompanyService.saveCompany(company);
    }


    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Optional<Company> existingCompany = iCompanyService.getCompanyById(id);
        if( existingCompany.isPresent()){
            company.setId(id);
            return ResponseEntity.ok(iCompanyService.saveCompany(company));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        Optional<Company> existingCompany = iCompanyService.getCompanyById(id);
        if( existingCompany.isPresent()){
            iCompanyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
