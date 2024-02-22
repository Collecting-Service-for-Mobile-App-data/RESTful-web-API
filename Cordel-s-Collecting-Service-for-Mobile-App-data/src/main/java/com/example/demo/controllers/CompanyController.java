package com.example.demo.controllers;

import com.example.demo.dto.CompanyDto;
import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        Iterable<Company> companies = this.companyService.getAll();
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company company : companies) {
            CompanyDto companyDto = new CompanyDto(company.getId(), company.getName());
            companyDtos.add(companyDto);
        }
        return ResponseEntity.ok(companyDtos);
    }
}
