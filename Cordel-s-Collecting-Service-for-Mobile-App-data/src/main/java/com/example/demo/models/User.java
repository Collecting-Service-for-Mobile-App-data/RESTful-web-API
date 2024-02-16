/**
 * The User class represents a user entity in the system.
 * Each user has an ID, email, password, role, reset password token, and associated company.
 */
package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "user")
@ApiModel(description = "Representation of a user in the system")
public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The unique identifier for the user")
    private long id;

    @NotNull
    @ApiModelProperty(notes = "The email address of the user")
    private String email;

    @NotNull
    @ApiModelProperty(notes = "The password of the user")
    private String password;

    @NotNull
    @ManyToOne
    @ApiModelProperty(notes = "The role of the user")
    private Role role;

    @ApiModelProperty(notes = "The token used for resetting the user's password")
    private String resetPasswordToken;

    @ManyToOne
    @ApiModelProperty(notes = "The company associated with the user")
    private Company company;

    /**
     * Constructs a user object with the given email, password, and role.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @param role     The role of the user.
     */
    public User(String email, String password, Role role) {
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    /**
     * Empty constructor needed for JPA.
     */
    public User() {
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return The ID of the user.
     */
    public long getId() {
        return id;
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
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the role of the user.
     *
     * @return The role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Retrieves the reset password token of the user.
     *
     * @return The reset password token of the user.
     */
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID to set for the user.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The role to set for the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Sets the reset password token of the user.
     *
     * @param resetPasswordToken The reset password token to set for the user.
     */
    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    /**
     * Checks if the user object is valid.
     * A user is considered valid if the email, password, role, and company are not null or empty.
     *
     * @return True if the user is valid, otherwise false.
     */
    public boolean isValid() {
        return !" ".equals(this.email) && !" ".equals(this.password) && this.email != null && this.password != null
                && this.company != null && this.role != null;
    }
}