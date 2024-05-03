package com.example.demo.dto;

import com.example.demo.models.Company;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * Data transfer object (DTO) for data from the sign-up form.
 */
@ApiModel(description = "Dto for creating a user")
public class SignupDto {

    @ApiModelProperty(notes = "Email for the user")
    private final String email;

    @ApiModelProperty(notes = "Password for the user")
    private final String password;

    @ApiModelProperty(notes = "Company for the user")
    private final Company company;

    public SignupDto(String username, String password, Company company) {
        this.email = username;
        this.password = password;
        this.company = company;
    }

    /**
     * Return email.
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return password.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    public Company getCompany() {
        return this.company;
    }

}
