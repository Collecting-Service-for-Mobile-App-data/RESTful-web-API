package com.example.demo.service;

import com.example.demo.models.Company;
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

  @Autowired
  CompanyService companyService;

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

  /**
   * Return all files based on company
   * @param id id of the compnay that you want to find files from
   * @return List of sql files
   */
  public List<SQLiteFiles> getAllByCompany(Long id ) {
    Company company = this.companyService.findById(id);
    return this.sqliteFilesRepository.findAllByCompany(company);
  }

  // Additional service methods can be defined here.
}
