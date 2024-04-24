package com.example.demo.service;

import com.example.demo.dto.SQLiteFileCreateDTO;
import com.example.demo.dto.SQLiteFileGetMetaDataDTO;
import com.example.demo.dto.SQLiteIsCheckedDTO;
import com.example.demo.models.Company;
import com.example.demo.models.SQLiteFiles;
import com.example.demo.models.User;
import com.example.demo.repository.SQLiteFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SQLiteFilesService {

  private final SQLiteFilesRepository sqliteFilesRepository;

  public SQLiteFiles findById(long id) {
    return this.sqliteFilesRepository.findById(id).orElse(null);
  }

  @Autowired
  public SQLiteFilesService(SQLiteFilesRepository sqliteFilesRepository) {
    this.sqliteFilesRepository = sqliteFilesRepository;
  }

  @Autowired
  CompanyService companyService;

  @Autowired
  AccessUserService accessUserService;

  public List<SQLiteFileGetMetaDataDTO> getAllSQLiteFiles() {
    List<SQLiteFiles> sqLiteFilesList = this.sqliteFilesRepository.findAll();
    return sqLiteFilesList.stream()
            .map(sqLiteFiles -> new SQLiteFileGetMetaDataDTO(sqLiteFiles.getId(), sqLiteFiles.getDate(), sqLiteFiles.getUser(), sqLiteFiles.isChecked()))
            .collect(Collectors.toList());
  }

  public SQLiteFiles getSQLiteFileById(Long id) {
    return sqliteFilesRepository.findById(id).orElse(null);
  }

  public SQLiteFiles saveSQLiteFile(SQLiteFiles sqliteFiles) {
    return sqliteFilesRepository.save(sqliteFiles);
  }

  public void deleteSQLiteFile(Long id) {
    sqliteFilesRepository.deleteById(id);
  }

  /**
   * Return all files based on company
   *
   * @param id id of the compnay that you want to find files from
   * @return List of sql files
   */
  public List<SQLiteFiles> getAllByCompany(Long id) {
    Company company = this.companyService.findById(id);
    return this.sqliteFilesRepository.findAllByCompany(company);
  }

  public void updateCheckedStatus(SQLiteIsCheckedDTO sqLiteIsCheckedDTO) {
    SQLiteFiles sqliteFileOptional = findById(sqLiteIsCheckedDTO.getId());
    sqliteFileOptional.setChecked(sqLiteIsCheckedDTO.isChecked());
    this.sqliteFilesRepository.save(sqliteFileOptional);
  }

  public void createSQLiteFile(SQLiteFileCreateDTO sqLiteFileDTO) {
    User user = this.accessUserService.findById(sqLiteFileDTO.getUserId());
    Company company = this.companyService.findById(sqLiteFileDTO.getCompanyId());
    SQLiteFiles sqLiteFiles = new SQLiteFiles(sqLiteFileDTO.getDate(), user, company, sqLiteFileDTO.getIsCheckd(), sqLiteFileDTO.getSqliteFile());
    this.sqliteFilesRepository.save(sqLiteFiles);
  }

  public byte[] getSqliteFil(Long id) {
    SQLiteFiles sqLiteFiles = getSQLiteFileById(id);
    return sqLiteFiles.getSQLiteFile();
  }
}
