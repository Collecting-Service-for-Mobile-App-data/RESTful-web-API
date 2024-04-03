package com.example.demo.controllers;

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

  @GetMapping("/")
  public ResponseEntity<List<SQLiteFiles>> getAllSQLiteFiles() {
    List<SQLiteFiles> list = sqliteFilesService.getAllSQLiteFiles();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SQLiteFiles> getSQLiteFileById(@PathVariable Long id) {
    return sqliteFilesService.getSQLiteFileById(id)
        .map(ResponseEntity::ok)
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/")
  public ResponseEntity<SQLiteFiles> createSQLiteFile(@RequestBody SQLiteFiles sqliteFiles) {
    SQLiteFiles savedFile = sqliteFilesService.saveSQLiteFile(sqliteFiles);
    return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SQLiteFiles> updateSQLiteFile(@PathVariable Long id, @RequestBody SQLiteFiles sqliteFiles) {
    return sqliteFilesService.getSQLiteFileById(id)
        .map(existingFile -> {
          sqliteFiles.setId(id); // Make sure to update the existing file.
          SQLiteFiles updatedFile = sqliteFilesService.saveSQLiteFile(sqliteFiles);
          return new ResponseEntity<>(updatedFile, HttpStatus.OK);
        })
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSQLiteFile(@PathVariable Long id) {
    return sqliteFilesService.getSQLiteFileById(id)
        .map(file -> {
          sqliteFilesService.deleteSQLiteFile(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        })
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}

