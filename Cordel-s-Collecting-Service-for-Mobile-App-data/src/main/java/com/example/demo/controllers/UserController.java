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

@Controller
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

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getAllUserBasedOnCompany(@PathVariable long id) { // Use @PathVariable instead of @RequestParam
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
    }

    /**
     * HTTP POST request to /authenticate
     *
     * @param authenticationRequest The request JSON object containing username and
     *                              password
     * @return OK + JWT token; Or UNAUTHORIZED
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = accessUserService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * HTTP GET request to /sessionuser
     *
     * @return OK + User object; Or UNAUTHORIZED
     */
    @GetMapping("/sessionuser")
    public ResponseEntity<?> getSesionUser() {
        User sessionUser = this.accessUserService.getSessionUser();
        System.out.println(sessionUser);
        if (sessionUser == null) {
            return new ResponseEntity<>("Didnt find user", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(sessionUser);
    }

    /**
     * Endpoint that creates a new user.
     *
     * @param signupDto the body of user that you want to create.
     * @return HTTP status CREATED if created, if not the INTERNAL_SERVER_ERROR.
     * @exception JSONException if an error occurs while creating the user.
     */
    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody SignupDto signupDto) {
        try {
            String errorMessage = accessUserService.tryCreateNewUser(signupDto.getEmail(),
                    signupDto.getPassword());
            ResponseEntity<String> response;
            if (errorMessage == null) {
                response = ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                response = ResponseEntity.badRequest().body(errorMessage);
            }
            return response;
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return ResponseEntity.badRequest().body(JSONEEXCEPTIONMESSAGE);
        }
    }
}
