package com.mdkhalilul.JobCheck.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class companyController {
    private final companyServices companyServ;

    public companyController(companyServices companyServ) {
        this.companyServ = companyServ;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company>companies=companyServ.findAllCompany();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
       Company newCompany= companyServ.createCompany(company);
        return new ResponseEntity<>(newCompany,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company,@PathVariable Long id){
        Company updatedCompany=companyServ.updateCompany(company,id);
        if(updatedCompany==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCompany,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company=companyServ.getCompanyById(id);
        if (company==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
       boolean isDeleted= companyServ.deleteCompanyById(id);
       if(!isDeleted){
           return  new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
       }
        return  new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
