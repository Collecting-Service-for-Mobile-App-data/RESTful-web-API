package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code CompanyTest} class provides unit tests for the {@code Company} model class.
 * It covers the testing of constructor functionality, property setting and getting,
 * as well as name validation within the {@code Company} class.
 */
class CompanyTest {

    private Company company;

    /**
     * Prepares the testing environment before each test.
     * This method initializes a {@code Company} object with a predefined name
     * to facilitate the testing of its properties and validation logic.
     */
    @BeforeEach
    void setUp() {
        company = new Company("Example Company");
    }

    /**
     * Tests the constructor of the {@code Company} class that includes a name parameter.
     * Verifies that the company name is correctly set through the constructor.
     */
    @Test
    void testConstructorWithName() {
        assertEquals("Example Company", company.getName(), "The company name should be set through the constructor");
    }

    /**
     * Tests the functionality of the {@code setName} method in the {@code Company} class.
     * Checks that the company name can be successfully changed and retrieved.
     */
    @Test
    void testSetName() {
        company.setName("New Example Company");
        assertEquals("New Example Company", company.getName(), "The company name should be changeable through setName method");
    }

    /**
     * Tests the setting and getting of a company's ID.
     * Ensures that the company ID can be accurately set and then retrieved.
     */
    @Test
    void testSetAndGetId() {
        company.setId(1L);
        assertEquals(1L, company.getId(), "The company ID should be correctly set and retrieved");
    }

    /**
     * Tests the validity of a company with a valid name.
     * Asserts that a company is considered valid when it has a non-null and non-empty name.
     */
    @Test
    void testCompanyValidityWithValidName() {
        assertTrue(company.isValid(), "The company should be considered valid with a non-null, non-empty name");
    }

    /**
     * Tests the validity of a company when the name is null.
     * Asserts that a company is considered invalid if its name is set to null.
     */
    @Test
    void testCompanyValidityWithNullName() {
        company.setName(null);
        assertFalse(company.isValid(), "The company should be considered invalid when the name is null");
    }

    /**
     * Tests the validity of a company with an empty name.
     * Asserts that a company with an empty name string is considered invalid.
     */
    @Test
    void testCompanyValidityWithEmptyName() {
        company.setName("");
        assertFalse(company.isValid(), "The company should be considered invalid when the name is empty");
    }

    /**
     * Tests the validity of a company with a blank name (whitespace only).
     * Asserts that a company is considered invalid if its name consists only of whitespace.
     */
    @Test
    void testCompanyValidityWithBlankName() {
        company.setName(" ");
        assertFalse(company.isValid(), "The company should be considered invalid when the name is only whitespace");
    }
}
