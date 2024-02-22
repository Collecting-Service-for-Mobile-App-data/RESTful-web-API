package com.example.demo.dto;

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

    public SignupDto(String username, String password) {
        this.email = username;
        this.password = password;
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

}
