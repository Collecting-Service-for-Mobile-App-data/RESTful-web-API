package com.example.demo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sqlite_files")
public class SQLiteFiles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;

  private boolean isChecked;

  @Lob
  @Column(length = 20971520)
  private byte[] sqliteFile;

  public SQLiteFiles() {
    // Default constructor
  }

  public SQLiteFiles(Date date, User user, Company company, boolean isChecked, byte[] sqliteFile) {
    this.date = date;
    this.user = user;
    this.company = company;
    this.isChecked = isChecked;
    this.sqliteFile = sqliteFile;
  }

  // Getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isChecked() {
    return this.isChecked;
  }

  public void setChecked(boolean checked) {
    this.isChecked = checked;
  }

  public void setSqliteFile(byte[] sqliteFile) {
    this.sqliteFile = sqliteFile;
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

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public byte[] getSQLiteFile() {
    return this.sqliteFile;
  }

  public void setSQLiteFile(byte[] sqliteFile) {
    this.sqliteFile = sqliteFile;
  }
}
