package com.example.demo.controllers;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.Company;
import com.example.demo.models.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AccessUserService;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.json.JSONException;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.swagger.annotations.*;

/**
 * Controller for managing user-related operations in the API.
 */
@Api(value = "UserController", description = "Controller for user management operations")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AccessUserService accessUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CompanyService companyService;

    private static final String JSON_EXCEPTION_MESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    /**
     * Retrieves all users associated with a given company ID.
     *
     * @param id the ID of the company
     * @return a list of users or an error message if no users found
     */
    @ApiOperation(value = "Get all users based on company id", response = UserDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "Company not found or No users found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/company/{id}")
    public ResponseEntity<?> getAllUserBasedOnCompany(@ApiParam(value = "ID of the company to fetch users", required = true) @PathVariable long id) {
        try {
            Company company = this.companyService.findById(id);
            if (company == null) {
                return new ResponseEntity<>("Didn't find company", HttpStatus.NOT_FOUND);
            }
            List<User> users = this.accessUserService.findAllUserByCompany(company);
            if (users == null || users.isEmpty()) {
                return new ResponseEntity<>("Didn't find any user from this company", HttpStatus.NOT_FOUND);
            }
            List<UserDTO> userDTOs = users.stream()
                    .map(user -> new UserDTO(user.getId(), user.getEmail(), user.getCompany()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        } catch (Exception e) {
            LOGGER.severe("Error in getAllUserBasedOnCompany: " + e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Authenticates a user and returns a JWT token if successful.
     *
     * @param authenticationRequest contains the email and password of the user
     * @return a JWT token or an error message if authentication fails
     */
    @ApiOperation(value = "Authenticate user and return JWT", response = AuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authentication successful"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@ApiParam(value = "Authentication request with email and password", required = true) @RequestBody AuthenticationRequest authenticationRequest) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = this.accessUserService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the session user details or an error message if not authenticated
     */
    @ApiOperation(value = "Retrieve the session user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrieved successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/sessionuser")
    public ResponseEntity<?> getSessionUser() {
        try {
            User sessionUser = this.accessUserService.getSessionUser();
            if (sessionUser == null) {
                return new ResponseEntity<>("Didn't find user", HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok(sessionUser);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving session user: " + e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new user based on the provided signup data.
     *
     * @param signupDto contains the necessary user information for registration
     * @return a response entity indicating success or failure of user creation
     */
    @ApiOperation(value = "Create a new user", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("")
    public ResponseEntity<String> createUser(@ApiParam(value = "Signup data transfer object", required = true) @RequestBody SignupDto signupDto) {
        try {
            String errorMessage = this.accessUserService.tryCreateNewUser(signupDto.getEmail(),
                    signupDto.getPassword(), signupDto.getCompany());
            ResponseEntity<String> response;
            if (errorMessage == null) {
                response = ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                response = ResponseEntity.badRequest().body(errorMessage);
            }
            return response;
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return ResponseEntity.badRequest().body(JSON_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return user details or an error message if user is not found
     */
    @ApiOperation(value = "Get user by ID", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrieved successfully"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserDTO> getUserById(@ApiParam(value = "The ID of the user", required = true) @PathVariable Long id) {
        try {
            UserDTO userDTO = this.accessUserService.findUserDTOById(id);
            if(userDTO != null) {
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.severe(SEVERE + e.getMessage());
        }
        return new  ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}
