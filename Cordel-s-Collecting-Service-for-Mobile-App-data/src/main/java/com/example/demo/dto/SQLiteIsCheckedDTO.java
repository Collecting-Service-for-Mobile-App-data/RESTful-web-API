package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO class for SQLite IsChecked information.
 */
@ApiModel(description = "DTO for SQLite IsChecked information")
public class SQLiteIsCheckedDTO {

  @ApiModelProperty(notes = "Checkbox indicating if an error is checked or not")
  private boolean isChecked;

  @ApiModelProperty(notes = "The ID associated with the IsChecked information")
  private long id;

  /**
   * Constructor for SQLiteIsCheckedDTO.
   * @param isChecked Checkbox indicating if an error is checked or not
   * @param id The ID associated with the IsChecked information
   */
  public SQLiteIsCheckedDTO(boolean isChecked, long id) {
    setChecked(isChecked);
    setId(id);
  }

  /**
   * Get the ID associated with the IsChecked information.
   * @return The ID associated with the IsChecked information
   */
  public long getId() {
    return id;
  }

  /**
   * Set the ID associated with the IsChecked information.
   * @param id The ID associated with the IsChecked information
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Check if an error is checked or not.
   * @return true if an error is checked, false otherwise
   */
  public boolean isChecked() {
    return this.isChecked;
  }

  /**
   * Set whether an error is checked or not.
   * @param checked Checkbox indicating if an error is checked or not
   */
  public void setChecked(boolean checked) {
    this.isChecked = checked;
  }
}
