package com.mdkhalilul.JobCheck.company.Impl;

import com.mdkhalilul.JobCheck.company.Company;
import com.mdkhalilul.JobCheck.company.companyInterface;
import com.mdkhalilul.JobCheck.company.companyServices;
import com.mdkhalilul.JobCheck.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImp implements companyServices {

    private final companyInterface companyI;

    public CompanyServiceImp(companyInterface companyI) {
        this.companyI = companyI;
    }

    @Override
    public List<Company> findAllCompany() {

        return companyI.findAll();
    }

    @Override
    public Company updateCompany(Company company, Long id) {
        Optional<Company>optionalCompany=companyI.findById(id);
        if(optionalCompany.isPresent()){
            Company updatedC=optionalCompany.get();
            updatedC.setDesc(company.getDesc());
            updatedC.setName(company.getName());
            companyI.save(updatedC);
            return updatedC;
        }
        return null;
    }

    @Override
    public Company createCompany(Company company) {
        if (company.getJobs() != null) {
            for (Job job : company.getJobs()) {
                job.setCompany(company); // Link each job to this company
            }
        }
        return companyI.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyI.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyI.existsById(id)){
            companyI.deleteById(id);
            return true;
        }
        return false;
    }
}
