package com.example.demo.controllers;

import com.example.demo.dto.SQLiteFileCreateDTO;
import com.example.demo.dto.SQLiteFileGetDTO;
import com.example.demo.dto.SQLiteIsCheckedDTO;
import com.example.demo.models.SQLiteFiles;
import com.example.demo.service.FTPService;
import com.example.demo.service.SQLiteFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
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
  public ResponseEntity<List<SQLiteFileGetDTO>> getAllSQLiteFiles() {
    List<SQLiteFileGetDTO> sqLiteFiles = this.sqliteFilesService.getAllSQLiteFiles();
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
          @RequestParam("file") MultipartFile file) {

    try {
      if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("No file uploaded");
      }
      SQLiteFileCreateDTO sqLiteFileDTO = new SQLiteFileCreateDTO(
              new Date(Long.parseLong(date)),
              userId,
              companyId,
              isChecked,
              file.getBytes()
      );
      this.sqliteFilesService.createSQLiteFile(sqLiteFileDTO);
      String remoteFilePath = "/var/sftp/Files/" + file.getOriginalFilename();
      boolean uploaded = this.ftpService.uploadFile(sqLiteFileDTO.getSqliteFile(), remoteFilePath);
      if (!uploaded) {
        return ResponseEntity.status(500).body("Failed to upload file to FTP server");
      }
      return ResponseEntity.ok("File uploaded successfully");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body("An error occurred while processing the request");
    }
  }

  @GetMapping("/file/{id}")
  public ResponseEntity<byte[]> getSQLiteFile(@PathVariable Long id) {
    SQLiteFiles sqliteFile = sqliteFilesService.getSQLiteFileById(id).orElse(null);

    if (sqliteFile == null) {
      return ResponseEntity.notFound().build();
    }
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String remoteFilePath = "/var/sftp/Files/" + sqliteFile.getUser().getId() + "_" + sqliteFile.getDate().getTime() + ".db"; // Adjust as needed
    boolean success = ftpService.downloadFile(remoteFilePath, outputStream);
    if (!success) {
      return ResponseEntity.status(500).body("Failed to retrieve file from FTP server".getBytes());
    }
    byte[] fileBytes = outputStream.toByteArray();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", "sqlite-file.db");
    return ResponseEntity.ok().headers(headers).body(fileBytes);
  }


}

