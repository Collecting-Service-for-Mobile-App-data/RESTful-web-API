package com.example.demo.controllers;

import com.example.demo.dto.SQLiteFileCreateDTO;
import com.example.demo.dto.SQLiteFileGetMetaDataDTO;
import com.example.demo.dto.SQLiteIsCheckedDTO;
import com.example.demo.models.SQLiteFiles;
import com.example.demo.service.FTPService;
import com.example.demo.service.SQLiteFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sqlite-files")
public class SQLiteFilesController {

  @Autowired
  private SQLiteFilesService sqliteFilesService;

  @Autowired
  FTPService ftpService;

  @GetMapping("/company/{id}")
  public ResponseEntity<List<SQLiteFiles>> getAllSQLiteFilesByCompany(@PathVariable long id) {
    List<SQLiteFiles> sqLiteFiles = this.sqliteFilesService.getAllByCompany(id);
    return new ResponseEntity<>(sqLiteFiles, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<SQLiteFileGetMetaDataDTO>> getAllSQLiteFiles() {
    List<SQLiteFileGetMetaDataDTO> sqLiteFiles = this.sqliteFilesService.getAllSQLiteFiles();
    return new ResponseEntity<>(sqLiteFiles, HttpStatus.OK);
  }

  @PutMapping("/checked")
  public  ResponseEntity<?> isChecked(@RequestBody SQLiteIsCheckedDTO sqLiteIsCheckedDTO) {
    this.sqliteFilesService.updateCheckedStatus(sqLiteIsCheckedDTO);
    return new ResponseEntity<>("Updated successfully", HttpStatus.OK);

  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(
          @RequestParam("date") String date,
          @RequestParam("userId") Long userId,
          @RequestParam("companyId") Long companyId,
          @RequestParam("isChecked") boolean isChecked,
          @RequestParam("file") MultipartFile file) throws IOException {

      SQLiteFileCreateDTO sqLiteFileDTO = new SQLiteFileCreateDTO(
              new Date(Long.parseLong(date)),
              userId,
              companyId,
              isChecked,
              file.getBytes()
      );
      this.sqliteFilesService.createSQLiteFile(sqLiteFileDTO);
      return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }

  @GetMapping("/download/{id}")
  public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
    byte[] file = this.sqliteFilesService.getSqliteFil(id);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"database.db\"");

    return new ResponseEntity<>(file, headers, HttpStatus.OK);
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
    this.sqliteFilesService.deleteSQLiteFile(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

