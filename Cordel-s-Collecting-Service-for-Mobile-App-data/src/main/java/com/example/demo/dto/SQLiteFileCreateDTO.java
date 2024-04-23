package com.example.demo.dto;

import java.util.Date;

/**
 * Data transfor object (DTO) class for SQLite files
 */
public class SQLiteFileCreateDTO {
    private Date date;
    private Long userId;
    private Long companyId;
    private boolean isCheckd;
    private byte[] sqliteFile;

    public SQLiteFileCreateDTO(Date date, Long userId, Long companyId, boolean isCheckd, byte[] sqliteFile) {
        setDate(date);
        setUserId(userId);
        setCompanyId(companyId);
        setIsCheckd(isCheckd);
        setSqliteFile(sqliteFile);
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public boolean getIsCheckd() {
        return this.isCheckd;
    }

    public void setIsCheckd(boolean isCheckd) {
        this.isCheckd = isCheckd;
    }

    public byte[] getSqliteFile() {
        return this.sqliteFile;
    }

    public void setSqliteFile(byte[] sqliteFile) {
        this.sqliteFile = sqliteFile;
    }
}
