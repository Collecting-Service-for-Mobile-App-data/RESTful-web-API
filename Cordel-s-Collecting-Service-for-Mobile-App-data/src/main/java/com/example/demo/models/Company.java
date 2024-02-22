/**
 * The Company class represents a company entity in the system.
 * Each company has an ID, name, and a set of associated users.
 */
package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "company")
@ApiModel(description = "Representation of a company in the system")
public class Company {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The unique identifier for the company")
    private long id;

    @NotNull
    @ApiModelProperty(notes = "The name of the company")
    private String name;

    @OneToMany(mappedBy = "company")
    @ApiModelProperty(notes = "The set of users associated with the company")
    private Set<User> users = new HashSet<>();

    /**
     * Constructs a company object with the given name.
     * @param name The name of the company.
     */
    public Company(String name) {
        setName(name);
    }

    /**
     * Empty constructor needed for JPA.
     */
    public Company() {}

    /**
     * Retrieves the ID of the company.
     * @return The ID of the company.
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the name of the company.
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ID of the company.
     * @param id The ID to set for the company.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the name of the company.
     * @param name The name to set for the company.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the company object is valid.
     * A company is considered valid if the name is not null or empty.
     * @return True if the company is valid, otherwise false.
     */
    public boolean isValid() {
        return !" ".equals(this.name) && this.name != null;
    }
}