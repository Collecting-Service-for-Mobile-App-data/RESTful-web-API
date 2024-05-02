/**
 * The UserTest class contains unit tests for the User class.
 * It tests various functionalities such as getting and setting user attributes,
 * as well as validating the user object.
 */
package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user; // Instance of the User class for testing.
    private Company company; // Company associated with the user.

    /**
     * Sets up the test environment before each test method execution.
     * Instantiates a Company and User object with test data.
     */
    @BeforeEach
    public void setUp() {
        this.company = new Company("Test Company");
        this.user = new User("test@example.com", "password", this.company);
    }

    /**
     * Tests the getEmail() method of the User class.
     * Verifies that the correct email is returned.
     */
    @Test
    void testGetEmail() {
        assertEquals("test@example.com", this.user.getEmail());
    }

    /**
     * Tests the setEmail() method of the User class.
     * Verifies that the email attribute is correctly set.
     */
    @Test
    void testSetEmail() {
        this.user.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", this.user.getEmail());
    }

    /**
     * Tests the getPassword() method of the User class.
     * Verifies that the correct password is returned.
     */
    @Test
    void testGetPassword() {
        assertEquals("password", this.user.getPassword());
    }

    /**
     * Tests the setPassword() method of the User class.
     * Verifies that the password attribute is correctly set.
     */
    @Test
    void testSetPassword() {
        this.user.setPassword("newPassword");
        assertEquals("newPassword", this.user.getPassword());
    }

    /**
     * Tests the getCompany() method of the User class.
     * Verifies that the correct company is returned.
     */
    @Test
    void testGetCompany() {
        assertEquals(this.company, this.user.getCompany());
    }

    /**
     * Tests the setCompany() method of the User class.
     * Verifies that the company attribute is correctly set.
     */
    @Test
    void testSetCompany() {
        Company newCompany = new Company("New Company");
        this.user.setCompany(newCompany);
        assertEquals(newCompany, this.user.getCompany());
    }

    /**
     * Tests the isValid() method of the User class with valid data.
     * Verifies that the user object is considered valid.
     */
    @Test
    void testIsValidWithValidData() {
        assertTrue(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with a null email.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithNullEmail() {
        this.user.setEmail(null);
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with an empty email.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithEmptyEmail() {
        this.user.setEmail("");
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with a whitespace email.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithWhitespaceEmail() {
        this.user.setEmail("   ");
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with a null password.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithNullPassword() {
        this.user.setPassword(null);
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with an empty password.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithEmptyPassword() {
        this.user.setPassword("");
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with a whitespace password.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithWhitespacePassword() {
        this.user.setPassword("   ");
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with a null company.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithNullCompany() {
        this.user.setCompany(null);
        assertFalse(this.user.isValid());
    }

    /**
     * Tests the isValid() method of the User class with null roles.
     * Verifies that the user object is considered invalid.
     */
    @Test
    void testIsValidWithNullRoles() {
        this.user.setRoles(null);
        assertFalse(this.user.isValid());
    }
}
