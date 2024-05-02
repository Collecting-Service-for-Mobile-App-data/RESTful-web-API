/**
 * The CompanyTest class contains unit tests for the Company class.
 * It tests various functionalities such as getting and setting company name,
 * as well as validating the company's name.
 */
package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    private Company company; // Instance of the Company class for testing.

    /**
     * Sets up the test environment before each test method execution.
     * Instantiates a Company object with the name "Test Company".
     */
    @BeforeEach
    void setUp() {
        this.company = new Company("Test Company");
    }

    /**
     * Tests the getName() method of the Company class.
     * Verifies that the correct name is returned.
     */
    @Test
    void testGetName() {
        assertEquals("Test Company", this.company.getName());
    }

    /**
     * Tests the setName() method of the Company class.
     * Verifies that the name is correctly set.
     */
    @Test
    void testSetName() {
        this.company.setName("New Name");
        assertEquals("New Name", this.company.getName());
    }

    /**
     * Tests the isValid() method of the Company class with a valid name.
     * Verifies that the company is considered valid.
     */
    @Test
    void testIsValidWithValidName() {
        assertTrue(this.company.isValid());
    }

    /**
     * Tests the isValid() method of the Company class with an empty name.
     * Verifies that the company is considered invalid.
     */
    @Test
    void testIsValidWithEmptyName() {
        this.company.setName("");
        assertFalse(this.company.isValid());
    }

    /**
     * Tests the isValid() method of the Company class with a null name.
     * Verifies that the company is considered invalid.
     */
    @Test
    void testIsValidWithNullName() {
        this.company.setName(null);
        assertFalse(this.company.isValid());
    }

    /**
     * Tests the isValid() method of the Company class with a whitespace name.
     * Verifies that the company is considered invalid.
     */
    @Test
    void testIsValidWithWhitespaceName() {
        this.company.setName("   ");
        assertFalse(this.company.isValid());
    }
}
