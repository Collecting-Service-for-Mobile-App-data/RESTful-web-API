/**
 * The User class represents a user entity in the system.
 * Each user has an ID, email, password, role, reset password token, and associated company.
 */
package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The User class represents a user entity in the system.
 */
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
    @ApiModelProperty(notes = "The role of the user")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new LinkedHashSet<>();

    private boolean active = true;

    @ManyToOne
    @ApiModelProperty(notes = "The company associated with the user")
    private Company company;

    /**
     * Constructs a user object with the given email, password, and role.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     */
    public User(String email, String password, Company company) {
        setEmail(email);
        setPassword(password);
        setCompany(company);
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
    public Set<Role> getRoles() {
        return this.roles;
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
     * @param roles The role to set for the user.
     */
    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Returns the company for the user
     *
     * @return company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the roles for the user
     *
     * @param roles what role the user want
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Sets the company of the user
     *
     * @param company the company you want the user to have.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Return true if user is active, false if not.
     *
     * @return true or false for if the user is active or not.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set true if the user is active, false if not.
     *
     * @param active choose true if it is active false if it is not active.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Add a role to the user
     *
     * @param role Role to add
     */
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     * Check if this user is an admin
     *
     * @return True if the user has admin role, false otherwise
     */
    public boolean isAdmin() {
        return this.hasRole("ROLE_ADMIN");
    }

    /**
     * Check if the user has a specified role
     *
     * @param roleName Name of the role
     * @return True if hte user has the role, false otherwise.
     */
    public boolean hasRole(String roleName) {
        boolean found = false;
        Iterator<Role> it = roles.iterator();
        while (!found && it.hasNext()) {
            Role role = it.next();
            if (role.getName().equals(roleName)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Checks if the user object is valid.
     * A user is considered valid if the email, password, role, and company are not null or empty.
     *
     * @return True if the user is valid, otherwise false.
     */
    public boolean isValid() {
        return this.email != null && !this.email.trim().isEmpty() &&
                this.password != null && !this.password.trim().isEmpty() &&
                this.company != null && this.roles != null;
    }
}