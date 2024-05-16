package com.example.demo.controllers;

import com.example.demo.dto.SQLiteFileCreateDTO;
import com.example.demo.dto.SQLiteFileGetMetaDataDTO;
import com.example.demo.dto.SQLiteIsCheckedDTO;
import com.example.demo.models.SQLiteFiles;
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
import java.util.logging.Logger;

import io.swagger.annotations.*;

/**
 * Controller for managing SQLite files operations including creating, retrieving, updating,
 * and deleting SQLite files.
 */
@Api(value = "SQLiteFilesController", description = "Controller for managing SQLite files")
@RestController
@RequestMapping("/api/sqlite-files")
public class SQLiteFilesController {

  @Autowired
  private SQLiteFilesService sqliteFilesService;

  private static final Logger LOGGER = Logger.getLogger(SQLiteFilesController.class.getName());

  /**
   * Retrieves all SQLite files associated with a specific company ID.
   *
   * @param id the company ID used to fetch the SQLite files
   * @return a list of SQLite files or an appropriate error response
   */
  @ApiOperation(value = "Get all SQLite files by company ID", response = SQLiteFiles.class, responseContainer = "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list"),
          @ApiResponse(code = 500, message = "Failed to retrieve SQLite files for company")
  })
  @GetMapping("/company/{id}")
  public ResponseEntity<?> getAllSQLiteFilesByCompany(@ApiParam(value = "Company ID to fetch SQLite files", required = true) @PathVariable long id) {
    try {
      List<SQLiteFileGetMetaDataDTO> sqLiteFiles = this.sqliteFilesService.getAllByCompany(id);
      if(sqLiteFiles != null) {
        return new ResponseEntity<>(sqLiteFiles, HttpStatus.OK);
      }
    } catch (Exception e) {
      LOGGER.severe("Something whent wronge" + e.getMessage());
    }
    return new ResponseEntity<>("Failed to retrieve SQLite files for company", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Retrieves metadata for all SQLite files stored in the database.
   *
   * @return a list of metadata for all SQLite files or an error message
   */
  @ApiOperation(value = "Get all SQLite files", response = SQLiteFileGetMetaDataDTO.class, responseContainer = "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved all SQLite files"),
          @ApiResponse(code = 500, message = "Failed to retrieve all SQLite files")
  })
  @GetMapping("/all")
  public ResponseEntity<?> getAllSQLiteFiles() {
    try {
      List<SQLiteFileGetMetaDataDTO> sqLiteFiles = this.sqliteFilesService.getAllSQLiteFiles();
      return new ResponseEntity<>(sqLiteFiles, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Failed to retrieve all SQLite files", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Updates the checked status of a specific SQLite file based on provided data.
   *
   * @param sqLiteIsCheckedDTO contains the ID of the file and the new checked status
   * @return a success message or an error response
   */
  @ApiOperation(value = "Update checked status of an SQLite file", response = String.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Updated successfully"),
          @ApiResponse(code = 500, message = "Failed to update check status")
  })
  @PutMapping("/checked")
  public ResponseEntity<?> isChecked(@ApiParam(value = "SQLite file check status DTO", required = true) @RequestBody SQLiteIsCheckedDTO sqLiteIsCheckedDTO) {
    try {
      this.sqliteFilesService.updateCheckedStatus(sqLiteIsCheckedDTO);
      return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Failed to update check status", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Uploads a new SQLite file along with its metadata.
   *
   * @param date the date of the file creation
   * @param userId the ID of the user uploading the file
   * @param companyId the company ID associated with the file
   * @param isChecked the initial check status of the file
   * @param file the file to be uploaded
   * @return a success message or an error response
   */
  @ApiOperation(value = "Upload a SQLite file", response = String.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "File uploaded successfully"),
          @ApiResponse(code = 400, message = "Failed to upload file"),
          @ApiResponse(code = 500, message = "Internal server error")
  })
  @PostMapping("/upload")
  public ResponseEntity<?> uploadFile(
          @ApiParam(value = "Date of the file", required = true) @RequestParam("date") String date,
          @ApiParam(value = "User ID of the file uploader", required = true) @RequestParam("userId") Long userId,
          @ApiParam(value = "Company ID related to the file", required = true) @RequestParam("companyId") Long companyId,
          @ApiParam(value = "Check status of the file", required = true) @RequestParam("isChecked") boolean isChecked,
          @ApiParam(value = "The SQLite file", required = true) @RequestParam("file") MultipartFile file) {
    try {
      SQLiteFileCreateDTO sqLiteFileDTO = new SQLiteFileCreateDTO(
              new Date(Long.parseLong(date)),
              userId,
              companyId,
              isChecked,
              file.getBytes()
      );
      this.sqliteFilesService.createSQLiteFile(sqLiteFileDTO);
      return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("Failed to upload file", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Downloads a SQLite file based on its ID.
   *
   * @param id the ID of the file to be downloaded
   * @return the file as a byte array along with appropriate HTTP headers or an error response
   */
  @ApiOperation(value = "Download a SQLite file", response = byte[].class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "File downloaded successfully"),
          @ApiResponse(code = 500, message = "Failed to download file")
  })
  @GetMapping("/download/{id}")
  public ResponseEntity<?> downloadFile(@ApiParam(value = "ID of the file to download", required = true) @PathVariable Long id) {
    try {
      byte[] file = this.sqliteFilesService.getSqliteFil(id);

      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"database.db\"");

      return new ResponseEntity<>(file, headers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Failed to download file", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Deletes a SQLite file based on its ID.
   *
   * @param id the ID of the file to be deleted
   * @return a response indicating the success or failure of the deletion process
   */
  @ApiOperation(value = "Delete a SQLite file", response = Void.class)
  @ApiResponses(value = {
          @ApiResponse(code = 204, message = "File deleted successfully"),
          @ApiResponse(code = 500, message = "Failed to delete file")
  })
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteFile(@ApiParam(value = "ID of the file to delete", required = true) @PathVariable Long id) {
    try {
      this.sqliteFilesService.deleteSQLiteFile(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>("Failed to delete file", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
