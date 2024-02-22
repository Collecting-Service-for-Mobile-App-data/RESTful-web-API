package com.example.demo.service;

import com.example.demo.models.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> getAll() {
        return this.companyRepository.findAll();
    }

    public Company findById(long id) {
        return this.companyRepository.findById(id).orElse(null);
    }
}
