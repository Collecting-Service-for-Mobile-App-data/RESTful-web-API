package com.example.demo.service;

import com.example.demo.models.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Company} entities.
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Retrieves all companies.
     *
     * @return An iterable collection of {@link Company} objects.
     */
    public Iterable<Company> getAll() {
        return this.companyRepository.findAll();
    }

    /**
     * Finds a company by its ID.
     *
     * @param id The ID of the company to find.
     * @return The {@link Company} object if found, otherwise null.
     */
    public Company findById(long id) {
        return this.companyRepository.findById(id).orElse(null);
    }
}
