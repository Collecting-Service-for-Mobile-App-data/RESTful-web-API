package com.example.demo.models;

import jakarta.persistence.*;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model class representing a SQLite file stored in the database.
 * This class includes details about the file such as its upload timestamp, associated user and company,
 * and whether the file has been checked or processed.
 */
@Entity
@Table(name = "sqlite_files")
@ApiModel(description = "Entity representing a SQLite file stored in the database.")
public class SQLiteFiles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(notes = "The unique identifier of the SQLite file.")
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @ApiModelProperty(notes = "Timestamp when the SQLite file was uploaded.")
  private Date date;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ApiModelProperty(notes = "The user who uploaded the SQLite file.")
  private User user;

  @ManyToOne
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  @ApiModelProperty(notes = "The company associated with the SQLite file.")
  private Company company;

  @ApiModelProperty(notes = "Flag to check if the SQLite file has been processed.")
  private boolean isChecked;

  @Lob
  @Column(length = 20971520)
  @ApiModelProperty(notes = "The binary content of the SQLite file.")
  private byte[] sqliteFile;

  /**
   * Default constructor used primarily by the JPA.
   */
  public SQLiteFiles() {
    // Default constructor for JPA
  }

  /**
   * Constructs a new SQLiteFiles instance with specified details.
   *
   * @param date        the timestamp when the file was uploaded
   * @param user        the user who uploaded the file
   * @param company     the company associated with the file
   * @param isChecked   flag indicating whether the file has been processed
   * @param sqliteFile  the binary content of the file
   */
  public SQLiteFiles(Date date, User user, Company company, boolean isChecked, byte[] sqliteFile) {
    this.date = date;
    this.user = user;
    this.company = company;
    this.isChecked = isChecked;
    this.sqliteFile = sqliteFile;
  }

  /**
   * Retrieves the unique identifier of the SQLite file.
   *
   * @return the unique identifier of the file
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the SQLite file.
   *
   * @param id the unique identifier to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Checks if the SQLite file has been processed.
   *
   * @return true if the file has been processed, otherwise false
   */
  public boolean isChecked() {
    return this.isChecked;
  }

  /**
   * Sets the processing status of the SQLite file.
   *
   * @param checked the processing status to set
   */
  public void setChecked(boolean checked) {
    this.isChecked = checked;
  }

  /**
   * Retrieves the timestamp when the SQLite file was uploaded.
   *
   * @return the upload timestamp of the file
   */
  public Date getDate() {
    return date;
  }

  /**
   * Sets the timestamp when the SQLite file was uploaded.
   *
   * @param date the upload timestamp to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * Retrieves the user who uploaded the SQLite file.
   *
   * @return the user who uploaded the file
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user who uploaded the SQLite file.
   *
   * @param user the user who uploaded the file
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Retrieves the company associated with the SQLite file.
   *
   * @return the company associated with the file
   */
  public Company getCompany() {
    return company;
  }

  /**
   * Sets the company associated with the SQLite file.
   *
   * @param company the company associated with the file
   */
  public void setCompany(Company company) {
    this.company = company;
  }

  /**
   * Retrieves the binary content of the SQLite file.
   *
   * @return the binary content of the file
   */
  public byte[] getSQLiteFile() {
    return this.sqliteFile;
  }

  /**
   * Sets the binary content of the SQLite file.
   *
   * @param sqliteFile the binary content to set
   */
  public void setSQLiteFile(byte[] sqliteFile) {
    this.sqliteFile = sqliteFile;
  }

  /**
   * Checks if SQLiteFile is valid
   * @return True if the SQLiteFile is valid, otherwise false.
   */
  public boolean isValid() {
    return this.date != null && this.user != null && this.company != null && this.sqliteFile != null;
  }
}
