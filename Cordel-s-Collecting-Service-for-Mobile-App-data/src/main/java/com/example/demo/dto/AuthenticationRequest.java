package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;


/**
 * Represents a request for authentication, containing user credentials.
 */
@ApiModel(description = "Dto class for authenticate request for user")
public class AuthenticationRequest {

    @ApiModelProperty(notes = "The email address of the user")
    private String email;

    @ApiModelProperty(notes = "The password of the user")
    private String password;

    public AuthenticationRequest() {
    }

    /**
     * Constructs an AuthenticationRequest object with the specified email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     */
    public AuthenticationRequest(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}