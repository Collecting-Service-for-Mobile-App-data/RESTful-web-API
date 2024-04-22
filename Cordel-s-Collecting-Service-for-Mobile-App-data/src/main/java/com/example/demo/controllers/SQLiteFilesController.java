package com.example.demo.controllers;

import com.example.demo.dto.SQLiteIsCheckedDTO;
import com.example.demo.models.SQLiteFiles;
import com.example.demo.service.SQLiteFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sqlite-files")
public class SQLiteFilesController {

  @Autowired
  private SQLiteFilesService sqliteFilesService;

  @GetMapping("/company/{id}")
  public ResponseEntity<List<SQLiteFiles>> getAllSQLiteFilesByCompany(@PathVariable long id) {
    List<SQLiteFiles> sqLiteFiles = this.sqliteFilesService.getAllByCompany(id);
    return new ResponseEntity<>(sqLiteFiles, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<SQLiteFiles>> getAllSQLiteFiles() {
    List<SQLiteFiles> sqLiteFiles = this.sqliteFilesService.getAllSQLiteFiles();
    return new ResponseEntity<>(sqLiteFiles, HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<SQLiteFiles> createSQLiteFile(@RequestBody SQLiteFiles sqliteFiles) {
    SQLiteFiles savedFile = sqliteFilesService.saveSQLiteFile(sqliteFiles);
    return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
  }

  @PutMapping("/checked")
  public  ResponseEntity<?> isChecked(@RequestBody SQLiteIsCheckedDTO sqLiteIsCheckedDTO) {
    this.sqliteFilesService.updateCheckedStatus(sqLiteIsCheckedDTO);
    return new ResponseEntity<>("Updated successfully", HttpStatus.OK);

  }
}

