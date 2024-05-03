/**
 * The RoleTest class contains unit tests for the Role class.
 * It tests various functionalities such as getting and setting role name,
 * as well as validating the role's name.
 */
package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role; // Instance of the Role class for testing.

    /**
     * Sets up the test environment before each test method execution.
     * Instantiates a Role object with the name "Test Role".
     */
    @BeforeEach
    void setUp() {
        this.role = new Role("Test Role");
    }

    /**
     * Tests the getName() method of the Role class.
     * Verifies that the correct name is returned.
     */
    @Test
    void testGetName() {
        assertEquals("Test Role", this.role.getName());
    }

    /**
     * Tests the setName() method of the Role class.
     * Verifies that the name is correctly set.
     */
    @Test
    void testSetName() {
        this.role.setName("New Name");
        assertEquals("New Name", this.role.getName());
    }

    /**
     * Tests the isValid() method of the Role class with a valid name.
     * Verifies that the role is considered valid.
     */
    @Test
    void testIsValidWithValidName() {
        assertTrue(this.role.isValid());
    }

    /**
     * Tests the isValid() method of the Role class with an empty name.
     * Verifies that the role is considered invalid.
     */
    @Test
    void testIsValidWithEmptyName() {
        this.role.setName("");
        assertFalse(this.role.isValid());
    }

    /**
     * Tests the isValid() method of the Role class with a null name.
     * Verifies that the role is considered invalid.
     */
    @Test
    void testIsValidWithNullName() {
        this.role.setName(null);
        assertFalse(this.role.isValid());
    }

    /**
     * Tests the isValid() method of the Role class with a whitespace name.
     * Verifies that the role is considered invalid.
     */
    @Test
    void testIsValidWithWhitespaceName() {
        this.role.setName("   ");
        assertFalse(this.role.isValid());
    }
}
