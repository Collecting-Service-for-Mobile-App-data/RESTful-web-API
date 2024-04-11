//package com.example.demo.models;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.HashSet;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * The {@code UserTest} class contains unit tests for testing the behaviors and functionalities
// * of the {@code User} class. It ensures that the {@code User} class properly handles initialization,
// * role management, and state checks.
// */
//class UserTest {
//
//    private User user;
//    private Role adminRole;
//    private Company company;
//
//    /**
//     * Sets up common objects for testing.
//     * This method is executed before each test method. It initializes a new {@code User} object,
//     * an admin {@code Role}, and a {@code Company} object, and associates them appropriately
//     * for testing different functionalities of the {@code User} class.
//     */
//    @BeforeEach
//    void setUp() {
//        user = new User("test@example.com", "password");
//        adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        company = new Company();
//        company.setName("Test Company");
//        user.setCompany(company);
//    }
//
//    /**
//     * Tests the constructor of the {@code User} class with explicit username and password.
//     * Asserts that the created {@code User} object correctly stores the provided email and password.
//     */
//    @Test
//    void testUserConstructor() {
//        User user = new User("testusername", "testuserpassword");
//        assertEquals("testusername", user.getEmail());
//        assertEquals("testuserpassword", user.getPassword());
//    }
//
//    /**
//     * Tests the constructor of the {@code User} class with blank email and password.
//     * Asserts that the constructor does not throw an error and correctly sets both fields, even if they are blank.
//     */
//    @Test
//    void testConstructorWithBlankEmailAndPassword() {
//        String email = "";
//        String password = "";
//        User user = new User(email, password);
//
//        assertTrue(email.equals(user.getEmail()) && password.equals(user.getPassword()),
//                "Constructor should set email and password, even if they are blank");
//    }
//
//    /**
//     * Verifies that a new {@code User} is active by default.
//     * Asserts the default active state of a user without any explicit activation.
//     */
//    @Test
//    void testUserIsActiveByDefault() {
//        assertTrue(user.isActive(), "User should be active by default");
//    }
//
//    /**
//     * Tests the addition of a role to a user.
//     * Asserts that after adding a role to a user, the user indeed has that role.
//     */
//    @Test
//    void testAddRole() {
//        user.addRole(adminRole);
//        assertTrue(user.getRoles().contains(adminRole), "User roles should include the added role");
//    }
//
//    /**
//     * Tests if a user is recognized as an admin after adding an admin role.
//     * Asserts that the user is considered an admin after the admin role has been added.
//     */
//    @Test
//    void testIsAdmin() {
//        user.addRole(adminRole);
//        assertTrue(user.isAdmin(), "User should be an admin after adding admin role");
//    }
//
//    /**
//     * Verifies that a user has a specific role by name.
//     * Asserts that the user has the specified role after it has been added.
//     */
//    @Test
//    void testHasRole() {
//        user.addRole(adminRole);
//        assertTrue(user.hasRole("ROLE_ADMIN"), "User should have ROLE_ADMIN");
//    }
//
//    /**
//     * Tests the validity of a user based on the presence of email, password, role, and company.
//     * Asserts that a user with these properties set is considered valid.
//     */
//    @Test
//    void testIsValid() {
//        HashSet<Role> roles = new HashSet<>();
//        roles.add(adminRole);
//        user.setRoles(roles);
//        assertTrue(user.isValid(), "User should be valid with email, password, role, and company");
//    }
//}
