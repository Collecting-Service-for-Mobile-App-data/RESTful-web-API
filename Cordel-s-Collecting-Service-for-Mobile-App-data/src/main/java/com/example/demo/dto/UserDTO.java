package com.example.demo.dto;

import com.example.demo.models.Company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO class for representing a user.
 */
@ApiModel(description = "DTO for representing a user")
public class UserDTO {

    @ApiModelProperty(notes = "The unique identifier for the user")
    private long id;

    @ApiModelProperty(notes = "The email address of the user")
    private String email;

    @ApiModelProperty(notes = "The company associated with the user")
    private Company company;

    /**
     * Constructor for UserDTO.
     * @param id The unique identifier for the user
     * @param email The email address of the user
     * @param company The company associated with the user
     */
    public UserDTO(long id, String email, Company company) {
        setId(id);
        setEmail(email);
        setCompany(company);
    }

    /**
     * Get the unique identifier for the user.
     * @return The unique identifier for the user
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier for the user.
     * @param id The unique identifier for the user
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the email address of the user.
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the user.
     * @param email The email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the company associated with the user.
     * @return The company associated with the user
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Set the company associated with the user.
     * @param company The company associated with the user
     */
    public void setCompany(Company company) {
        this.company = company;
    }
}
