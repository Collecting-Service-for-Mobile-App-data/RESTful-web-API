package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class SQLiteIsCheckedDTO {

  @ApiModelProperty(notes = "Checkbox for an error")
  private boolean isChecked;
  private long id;

  public SQLiteIsCheckedDTO(boolean isChecked, long id) {
    setChecked(isChecked);
    setId(id);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isChecked() {
    return this.isChecked;
  }

  public void setChecked(boolean checked) {
    this.isChecked = checked;
  }
}
