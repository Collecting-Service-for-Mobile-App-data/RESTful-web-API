package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * Data Transfer Object (DTO) representing a company.
 */
@ApiModel(description = "DTO class for company")
public class CompanyDto {

    @ApiModelProperty(notes = "Id of the company")
    private long id;

    @ApiModelProperty(notes = "Name of the company")
    private String name;

    /**
     * Constructs a new CompanyDto object with the specified id and name.
     *
     * @param id   The unique identifier of the company.
     * @param name The name of the company.
     */
    public CompanyDto(long id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * Retrieves the unique identifier of the company.
     *
     * @return The id of the company.
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the name of the company.
     *
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the unique identifier of the company.
     *
     * @param id The id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the name of the company.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}