package com.mdkhalilul.JobCheck.company;

import org.springframework.stereotype.Service;

import java.util.List;

public interface companyServices {
    List<Company> findAllCompany();
    Company updateCompany(Company company,Long id);
    Company createCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);
}
