/**
 * The SQLiteFilesTest class contains unit tests for the SQLiteFiles class.
 * It tests various functionalities such as getting and setting file attributes,
 * as well as validating the SQLite file object.
 */
package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class SQLiteFilesTest {

    private SQLiteFiles sqliteFile; // Instance of the SQLiteFiles class for testing.
    private User user; // User associated with the SQLite file.
    private Company company; // Company associated with the SQLite file.

    /**
     * Sets up the test environment before each test method execution.
     * Instantiates a User, Company, and SQLiteFiles object with test data.
     */
    @BeforeEach
    void setUp() {
        this.user = new User("test@example.com", "password", company);
        this.company = new Company("Test Company");
        Date currentDate = new Date();
        byte[] fileContent = new byte[1024];
        this.sqliteFile = new SQLiteFiles(currentDate, this.user, this.company, false, fileContent);
    }

    /**
     * Tests the getDate() method of the SQLiteFiles class.
     * Verifies that the date attribute is not null.
     */
    @Test
    void testGetDate() {
        assertNotNull(this.sqliteFile.getDate());
    }

    /**
     * Tests the setDate() method of the SQLiteFiles class.
     * Verifies that the date attribute is correctly set.
     */
    @Test
    void testSetDate() {
        Date newDate = new Date();
        this.sqliteFile.setDate(newDate);
        assertEquals(newDate, this.sqliteFile.getDate());
    }

    /**
     * Tests the getUser() method of the SQLiteFiles class.
     * Verifies that the correct user is returned.
     */
    @Test
    void testGetUser() {
        assertEquals(this.user, this.sqliteFile.getUser());
    }

    /**
     * Tests the setUser() method of the SQLiteFiles class.
     * Verifies that the user attribute is correctly set.
     */
    @Test
    void testSetUser() {
        User newUser = new User("newuser@example.com", "newpassword", company);
        this.sqliteFile.setUser(newUser);
        assertEquals(newUser, this.sqliteFile.getUser());
    }

    /**
     * Tests the getCompany() method of the SQLiteFiles class.
     * Verifies that the correct company is returned.
     */
    @Test
    void testGetCompany() {
        assertEquals(this.company, this.sqliteFile.getCompany());
    }

    /**
     * Tests the setCompany() method of the SQLiteFiles class.
     * Verifies that the company attribute is correctly set.
     */
    @Test
    void testSetCompany() {
        Company newCompany = new Company("New Company");
        this.sqliteFile.setCompany(newCompany);
        assertEquals(newCompany, this.sqliteFile.getCompany());
    }

    /**
     * Tests the isChecked() method of the SQLiteFiles class.
     * Verifies that the checked attribute returns the correct boolean value.
     */
    @Test
    void testIsChecked() {
        assertFalse(this.sqliteFile.isChecked());
    }

    /**
     * Tests the setChecked() method of the SQLiteFiles class.
     * Verifies that the checked attribute is correctly set.
     */
    @Test
    void testSetChecked() {
        this.sqliteFile.setChecked(true);
        assertTrue(this.sqliteFile.isChecked());
    }

    /**
     * Tests the getSQLiteFile() method of the SQLiteFiles class.
     * Verifies that the SQLite file content is not null.
     */
    @Test
    void testGetSQLiteFile() {
        assertNotNull(this.sqliteFile.getSQLiteFile());
    }

    /**
     * Tests the setSQLiteFile() method of the SQLiteFiles class.
     * Verifies that the SQLite file content is correctly set.
     */
    @Test
    void testSetSQLiteFile() {
        byte[] newContent = new byte[2048];
        this.sqliteFile.setSQLiteFile(newContent);
        assertEquals(newContent, this.sqliteFile.getSQLiteFile());
    }

    /**
     * Tests the isValid() method of the SQLiteFiles class with valid data.
     * Verifies that the SQLite file object is considered valid.
     */
    @Test
    void testIsValidWithValidData() {
        assertTrue(this.sqliteFile.isValid());
    }

    /**
     * Tests the isValid() method of the SQLiteFiles class with a null date.
     * Verifies that the SQLite file object is considered invalid.
     */
    @Test
    void testIsValidWithNullDate() {
        this.sqliteFile.setDate(null);
        assertFalse(this.sqliteFile.isValid());
    }

    /**
     * Tests the isValid() method of the SQLiteFiles class with a null user.
     * Verifies that the SQLite file object is considered invalid.
     */
    @Test
    void testIsValidWithNullUser() {
        this.sqliteFile.setUser(null);
        assertFalse(this.sqliteFile.isValid());
    }

    /**
     * Tests the isValid() method of the SQLiteFiles class with a null company.
     * Verifies that the SQLite file object is considered invalid.
     */
    @Test
    void testIsValidWithNullCompany() {
        this.sqliteFile.setCompany(null);
        assertFalse(this.sqliteFile.isValid());
    }

    /**
     * Tests the isValid() method of the SQLiteFiles class with a null SQLite file content.
     * Verifies that the SQLite file object is considered invalid.
     */
    @Test
    void testIsValidWithNullSQLiteFile() {
        this.sqliteFile.setSQLiteFile(null);
        assertFalse(this.sqliteFile.isValid());
    }
}
