package com.example.demo.dto;

import com.example.demo.models.User;

import java.util.Date;

public class SQLiteFileGetMetaDataDTO {
    private Long id;
    private Date date;
    private User user;

    private boolean isChecked;

    //Default constructor
    public SQLiteFileGetMetaDataDTO(Long id, Date date, User user, boolean isChecked) {
        setId(id);
        setDate(date);
        setUser(user);
        setIsChecked(isChecked);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIsChecked(boolean chect) {
        this.isChecked = chect;
    }

    public boolean getIsChecked() {
        return isChecked;
    }
}
