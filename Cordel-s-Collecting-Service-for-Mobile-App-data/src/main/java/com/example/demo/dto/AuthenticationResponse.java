package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * Data that we will send as a response to the user when the authentication is successful.
 */
@ApiModel(description = "Dto class for authenticate response with jwt")
public class AuthenticationResponse {

    @ApiModelProperty(notes = "jwt token the user get in response for authentication")
    private final String jwt;

    /**
     * Default constructor for AuthenticationResponse.
     *
     * @param jwt the java web token the user gets.
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Returns java web token.
     * @return jwt.
     */
    public String getJwt() {
        return jwt;
    }
}