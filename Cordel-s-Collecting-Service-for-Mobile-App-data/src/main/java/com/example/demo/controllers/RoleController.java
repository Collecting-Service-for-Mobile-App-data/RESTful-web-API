package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.logging.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for managing roles within the application.
 * This includes operations to retrieve all roles defined in the system.
 */
@Api(value = "RoleController", description = "Controller for managing roles")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static final Logger LOGGER = Logger.getLogger(RoleController.class.getName());

    /**
     * Retrieves a list of all roles available in the system. This method handles the business logic
     * for querying the database through the RoleService and returns the data as a ResponseEntity.
     *
     * @return a ResponseEntity containing either a list of Role objects or an error message
     *         in case of failure.
     */
    @ApiOperation(value = "Retrieve all roles", response = Role.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of roles"),
            @ApiResponse(code = 500, message = "Internal server error while retrieving roles")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<Role> roles = this.roleService.getAll();
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving roles: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Failed to retrieve roles due to internal error.");
        }
    }
}
