/**
 * The Role class represents a role entity in the system.
 * Each role has an ID, name, and a set of associated users.
 */
package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "role")
@ApiModel(description = "Representation of a role in the system")
public class Role {

    @GeneratedValue
    @Id
    @ApiModelProperty(notes = "The unique identifier for the role")
    private long id;

    @NotNull
    @ApiModelProperty(notes = "The name of the role")
    private String name;

    @OneToMany()
    @ApiModelProperty(notes = "The set of users associated with the role")
    private Set<User> users = new HashSet<>();

    /**
     * Constructs a role object with the given name.
     * @param name The name of the role.
     */
    public Role(String name) {
        setName(name);
    }

    /**
     * Empty constructor needed for JPA.
     */
    public Role() {}

    /**
     * Retrieves the ID of the role.
     * @return The ID of the role.
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the name of the role.
     * @return The name of the role.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ID of the role.
     * @param id The ID to set for the role.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the name of the role.
     * @param name The name to set for the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the role object is valid.
     * A role is considered valid if the name is not null or empty.
     * @return True if the role is valid, otherwise false.
     */
    public boolean isValid() {
        return !" ".equals(this.name) && this.name != null;
    }
}