package com.example.demo.service;

import com.example.demo.models.SQLiteFiles;
import com.example.demo.repository.SQLiteFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SQLiteFilesService {

  private final SQLiteFilesRepository sqliteFilesRepository;

  @Autowired
  public SQLiteFilesService(SQLiteFilesRepository sqliteFilesRepository) {
    this.sqliteFilesRepository = sqliteFilesRepository;
  }

  public List<SQLiteFiles> getAllSQLiteFiles() {
    return sqliteFilesRepository.findAll();
  }

  public Optional<SQLiteFiles> getSQLiteFileById(Long id) {
    return sqliteFilesRepository.findById(id);
  }

  public SQLiteFiles saveSQLiteFile(SQLiteFiles sqliteFiles) {
    return sqliteFilesRepository.save(sqliteFiles);
  }

  public void deleteSQLiteFile(Long id) {
    sqliteFilesRepository.deleteById(id);
  }

  // Additional service methods can be defined here.
}
