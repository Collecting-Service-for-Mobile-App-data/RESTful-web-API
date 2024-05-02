package com.example.demo.controllers;

import com.example.demo.dto.CompanyDto;
import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for handling operations related to companies within the system.
 * Provides endpoints for retrieving a list of all companies.
 */
@Api(value = "CompanyController", description = "Controller for managing companies")
@Controller
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private static final Logger LOGGER = Logger.getLogger(CompanyController.class.getName());

    /**
     * Retrieves all companies registered in the system and maps them to CompanyDto objects.
     * This method manages the interaction with the CompanyService to fetch all company entities
     * and convert them into a more accessible data transfer object (DTO) format.
     *
     * @return a ResponseEntity containing a list of CompanyDto objects or an error message
     *         if an internal server error occurs during the process.
     */
    @ApiOperation(value = "Retrieve all companies", response = CompanyDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of companies"),
            @ApiResponse(code = 500, message = "Internal server error while retrieving companies")
    })
    @GetMapping("/companies")
    public ResponseEntity<?> getAllCompanies() {
        try {
            Iterable<Company> companies = this.companyService.getAll();
            List<CompanyDto> companyDtos = new ArrayList<>();
            for (Company company : companies) {
                CompanyDto companyDto = new CompanyDto(company.getId(), company.getName());
                companyDtos.add(companyDto);
            }
            return ResponseEntity.ok(companyDtos);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving companies: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Failed to retrieve company data due to an internal error.");
        }
    }
}
