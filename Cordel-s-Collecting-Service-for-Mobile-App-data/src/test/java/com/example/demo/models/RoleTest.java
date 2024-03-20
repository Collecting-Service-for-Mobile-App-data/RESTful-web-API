package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code RoleTest} class implements unit tests for the {@code Role} model class.
 * It focuses on testing the creation and manipulation of {@code Role} objects, including
 * setting and getting properties such as name and ID, as well as validating role names.
 */
class RoleTest {

    private Role role;

    /**
     * Sets up a new {@code Role} object before each test.
     * This method initializes a {@code Role} object with a predefined name "ROLE_USER"
     * to be used in subsequent tests.
     */
    @BeforeEach
    void setUp() {
        role = new Role("ROLE_USER");
    }

    /**
     * Tests the {@code Role} constructor with a name parameter.
     * Asserts that the role name is correctly set through the constructor.
     */
    @Test
    void testConstructorWithName() {
        assertEquals("ROLE_USER", role.getName(), "The role name should be set through the constructor");
    }

    /**
     * Tests the ability to change a role's name using {@code setName}.
     * Asserts that the role name can be changed and correctly retrieved afterwards.
     */
    @Test
    void testSetName() {
        role.setName("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", role.getName(), "The role name should be changeable through setName method");
    }

    /**
     * Tests setting and getting a role's ID.
     * Asserts that the role ID can be set and correctly retrieved.
     */
    @Test
    void testSetAndGetId() {
        role.setId(1L);
        assertEquals(1L, role.getId(), "The role ID should be correctly set and retrieved");
    }

    /**
     * Tests the validity of a role with a valid name.
     * Asserts that a role with a non-null, non-empty name is considered valid.
     */
    @Test
    void testRoleValidityWithValidName() {
        assertTrue(role.isValid(), "The role should be considered valid with a non-null, non-empty name");
    }

    /**
     * Tests the role validity when the name is set to null.
     * Asserts that a role with a null name is considered invalid.
     */
    @Test
    void testRoleValidityWithNullName() {
        role.setName(null);
        assertFalse(role.isValid(), "The role should be considered invalid when the name is null");
    }

    /**
     * Tests the role validity when the name is an empty string.
     * Asserts that a role with an empty name is considered invalid.
     */
    @Test
    void testRoleValidityWithEmptyName() {
        role.setName("");
        assertFalse(role.isValid(), "The role should be considered invalid when the name is empty");
    }

    /**
     * Tests the role validity when the name is a blank string (whitespace only).
     * Asserts that a role with a name that is only whitespace is considered invalid.
     */
    @Test
    void testRoleValidityWithBlankName() {
        role.setName(" ");
        assertFalse(role.isValid(), "The role should be considered invalid when the name is only whitespace");
    }
}
