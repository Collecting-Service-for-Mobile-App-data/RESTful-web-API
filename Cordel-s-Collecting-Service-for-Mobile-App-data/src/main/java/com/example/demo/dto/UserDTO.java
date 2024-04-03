package com.example.demo.dto;

import com.example.demo.models.Company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO for representing a user")
public class UserDTO {

    @ApiModelProperty(notes = "The unique identifier for the user")
    private long id;

    @ApiModelProperty(notes = "The email address of the user")
    private String email;

    @ApiModelProperty(notes = "The company associated with the user")
    private Company company;

    public UserDTO(long id, String email, Company company) {
        setId(id);
        setEmail(email);
        setCompany(company);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
