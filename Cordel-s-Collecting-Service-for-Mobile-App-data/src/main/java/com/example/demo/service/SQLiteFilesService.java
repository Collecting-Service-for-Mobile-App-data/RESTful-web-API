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

/**
 * Service class for managing {@link SQLiteFiles}.
 */
@Service
public class SQLiteFilesService {

  private final SQLiteFilesRepository sqliteFilesRepository;

  /**
   * Constructs a new SQLiteFilesService.
   *
   * @param sqliteFilesRepository The repository for SQLiteFiles.
   */
  @Autowired
  public SQLiteFilesService(SQLiteFilesRepository sqliteFilesRepository) {
    this.sqliteFilesRepository = sqliteFilesRepository;
  }

  @Autowired
  CompanyService companyService;

  @Autowired
  AccessUserService accessUserService;

  /**
   * Finds a SQLite file by its ID.
   *
   * @param id The ID of the SQLite file to find.
   * @return The {@link SQLiteFiles} object if found, otherwise null.
   */
  public SQLiteFiles findById(long id) {
    return this.sqliteFilesRepository.findById(id).orElse(null);
  }

  /**
   * Retrieves metadata of all SQLite files.
   *
   * @return A list of {@link SQLiteFileGetMetaDataDTO} objects containing metadata of SQLite files.
   */
  public List<SQLiteFileGetMetaDataDTO> getAllSQLiteFiles() {
    List<SQLiteFiles> sqLiteFilesList = this.sqliteFilesRepository.findAll();
    return sqLiteFilesList.stream()
            .map(sqLiteFiles -> new SQLiteFileGetMetaDataDTO(sqLiteFiles.getId(), sqLiteFiles.getDate(), sqLiteFiles.getUser(), sqLiteFiles.isChecked()))
            .collect(Collectors.toList());
  }

  /**
   * Retrieves a SQLite file by its ID.
   *
   * @param id The ID of the SQLite file to retrieve.
   * @return The {@link SQLiteFiles} object if found, otherwise null.
   */
  public SQLiteFiles getSQLiteFileById(Long id) {
    return sqliteFilesRepository.findById(id).orElse(null);
  }

  /**
   * Saves a SQLite file.
   *
   * @param sqliteFiles The SQLite file to save.
   * @return The saved {@link SQLiteFiles} object.
   */
  public SQLiteFiles saveSQLiteFile(SQLiteFiles sqliteFiles) {
    return sqliteFilesRepository.save(sqliteFiles);
  }

  /**
   * Deletes a SQLite file by its ID.
   *
   * @param id The ID of the SQLite file to delete.
   */
  public void deleteSQLiteFile(Long id) {
    sqliteFilesRepository.deleteById(id);
  }

  /**
   * Returns all files based on company.
   *
   * @param id The ID of the company to retrieve files from.
   * @return A list of SQLite files associated with the company.
   */
  public List<SQLiteFileGetMetaDataDTO> getAllByCompany(Long id) {
    Company company = this.companyService.findById(id);
     List<SQLiteFiles> sqLiteFilesList = this.sqliteFilesRepository.findAllByCompany(company);
     if(sqLiteFilesList.isEmpty()) {
       return null;
     }
    return sqLiteFilesList.stream()
            .map(sqLiteFiles -> new SQLiteFileGetMetaDataDTO(sqLiteFiles.getId(), sqLiteFiles.getDate(), sqLiteFiles.getUser(), sqLiteFiles.isChecked()))
            .collect(Collectors.toList());
  }

  /**
   * Updates the checked status of a SQLite file.
   *
   * @param sqLiteIsCheckedDTO DTO containing the ID of the SQLite file and its new checked status.
   */
  public void updateCheckedStatus(SQLiteIsCheckedDTO sqLiteIsCheckedDTO) {
    SQLiteFiles sqliteFileOptional = findById(sqLiteIsCheckedDTO.getId());
    sqliteFileOptional.setChecked(sqLiteIsCheckedDTO.isChecked());
    this.sqliteFilesRepository.save(sqliteFileOptional);
  }

  /**
   * Creates a new SQLite file.
   *
   * @param sqLiteFileDTO DTO containing information about the new SQLite file.
   */
  public void createSQLiteFile(SQLiteFileCreateDTO sqLiteFileDTO) {
    User user = this.accessUserService.findById(sqLiteFileDTO.getUserId());
    Company company = this.companyService.findById(sqLiteFileDTO.getCompanyId());
    SQLiteFiles sqLiteFiles = new SQLiteFiles(sqLiteFileDTO.getDate(), user, company, sqLiteFileDTO.getIsCheckd(), sqLiteFileDTO.getSqliteFile());
    this.sqliteFilesRepository.save(sqLiteFiles);
  }

  /**
   * Retrieves the SQLite file content by its ID.
   *
   * @param id The ID of the SQLite file to retrieve.
   * @return The content of the SQLite file as a byte array.
   */
  public byte[] getSqliteFil(Long id) {
    SQLiteFiles sqLiteFiles = getSQLiteFileById(id);
    return sqLiteFiles.getSQLiteFile();
  }
}
