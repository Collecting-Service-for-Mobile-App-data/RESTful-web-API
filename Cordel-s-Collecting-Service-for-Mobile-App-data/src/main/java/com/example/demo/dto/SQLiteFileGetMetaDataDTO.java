package com.example.demo.dto;

import com.example.demo.models.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * DTO class for SQLite file metadata.
 */
@ApiModel(description = "DTO for SQLite file metadata")
public class SQLiteFileGetMetaDataDTO {

    @ApiModelProperty(notes = "The ID of the metadata")
    private Long id;

    @ApiModelProperty(notes = "The date of the metadata")
    private Date date;

    @ApiModelProperty(notes = "The user associated with the metadata")
    private User user;

    @ApiModelProperty(notes = "Flag indicating whether the metadata is checked or not")
    private boolean isChecked;

    /**
     * Default constructor.
     * @param id The ID of the metadata
     * @param date The date of the metadata
     * @param user The user associated with the metadata
     * @param isChecked Flag indicating whether the metadata is checked or not
     */
    public SQLiteFileGetMetaDataDTO(Long id, Date date, User user, boolean isChecked) {
        setId(id);
        setDate(date);
        setUser(user);
        setIsChecked(isChecked);
    }

    /**
     * Get the ID of the metadata.
     * @return The ID of the metadata
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the metadata.
     * @param id The ID of the metadata
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the date of the metadata.
     * @return The date of the metadata
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date of the metadata.
     * @param date The date of the metadata
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the user associated with the metadata.
     * @return The user associated with the metadata
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user associated with the metadata.
     * @param user The user associated with the metadata
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Set whether the metadata is checked or not.
     * @param isChecked Flag indicating whether the metadata is checked or not
     */
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * Check whether the metadata is checked or not.
     * @return true if the metadata is checked, false otherwise
     */
    public boolean getIsChecked() {
        return isChecked;
    }
}
