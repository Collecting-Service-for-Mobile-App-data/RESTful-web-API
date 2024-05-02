package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * Data transfer object (DTO) class for SQLite files
 */
@ApiModel(description = "DTO for SQLite files")
public class SQLiteFileCreateDTO {

    /**
     * Date of creation
     */
    @ApiModelProperty(notes = "Date of creation")
    private Date date;

    /**
     * User ID
     */
    @ApiModelProperty(notes = "User ID")
    private Long userId;

    /**
     * Company ID
     */
    @ApiModelProperty(notes = "Company ID")
    private Long companyId;

    /**
     * Indicates whether the file is checked
     */
    @ApiModelProperty(notes = "Indicates whether the file is checked")
    private boolean isCheckd;

    /**
     * SQLite file as byte array
     */
    @ApiModelProperty(notes = "SQLite file as byte array")
    private byte[] sqliteFile;

    /**
     * Constructor for SQLiteFileCreateDTO
     * @param date Date of creation
     * @param userId User ID
     * @param companyId Company ID
     * @param isCheckd Indicates whether the file is checked
     * @param sqliteFile SQLite file as byte array
     */
    public SQLiteFileCreateDTO(Date date, Long userId, Long companyId, boolean isCheckd, byte[] sqliteFile) {
        setDate(date);
        setUserId(userId);
        setCompanyId(companyId);
        setIsCheckd(isCheckd);
        setSqliteFile(sqliteFile);
    }

    /**
     * Get the date of creation
     * @return Date of creation
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Set the date of creation
     * @param date Date of creation
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the User ID
     * @return User ID
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Set the User ID
     * @param userId User ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the Company ID
     * @return Company ID
     */
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * Set the Company ID
     * @param companyId Company ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * Check if the file is checked
     * @return true if the file is checked, false otherwise
     */
    public boolean getIsCheckd() {
        return this.isCheckd;
    }

    /**
     * Set whether the file is checked
     * @param isCheckd true if the file is checked, false otherwise
     */
    public void setIsCheckd(boolean isCheckd) {
        this.isCheckd = isCheckd;
    }

    /**
     * Get the SQLite file as byte array
     * @return SQLite file as byte array
     */
    public byte[] getSqliteFile() {
        return this.sqliteFile;
    }

    /**
     * Set the SQLite file as byte array
     * @param sqliteFile SQLite file as byte array
     */
    public void setSqliteFile(byte[] sqliteFile) {
        this.sqliteFile = sqliteFile;
    }
}
