package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.Company;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.AccessUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.List;
import java.util.Optional;

/**
 * Service class implementing {@link UserDetailsService} for managing {@link User} entities.
 */
@Service
public class AccessUserService implements UserDetailsService {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String MIN_ONE_CAPITALLETTER = ".*[A-Z]+.*";
    private static final String MIN_ONE_SMALLLETTER = ".*[a-z]+.*";
    private static final String MIN_ONE_NUMBER = ".*\\d+.*";
    private static final String CORRECT_EMAIL_REQUIREMENTS =  "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new AccessUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("Email " + email + "not found");
        }
    }

    /**
     * Get the user which is authenticated for the current session.
     *
     * @return User object or null if no user has logged in
     */
    public User getSessionUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Check if user with given username exists in the database.
     *
     * @param email email of the user to check, case-sensitive
     * @return True if user exists, false otherwise
     */
    private boolean userExists(String email) {
        try {
            loadUserByUsername(email);
            return true;
        } catch (UsernameNotFoundException ex) {
            return false;
        }
    }

    /**
     * Tries to create a new user.
     * If the requirement are not meat an error message is returned else the user is created.
     * @param email the mail you want for the user.
     * @param password the password you want for the user.
     * @return error massage if user didn't get created. If created it returns null.
     */
    public String tryCreateNewUser(String email, String password, Company company) {
        String errorMessage;
        errorMessage = checkEmailRequirements(email);
        if(errorMessage != null) {
            return errorMessage;
        }
        errorMessage = checkPasswordRequirements(password);
        if(errorMessage == null) {
            createUser(email,password, company);
        }
        return errorMessage;
    }

    /**
     * Check if email matches the requirements
     *
     * @param email a email to check
     * @return null of all ok, error message on error
     */
    private String checkEmailRequirements(String email) {
        String errorMessage = null;
        if(email == null || email.isEmpty()) {
            errorMessage = "Email can't be empty";
        }
        else if (userExists(email)) {
            errorMessage = "Email already taken";
        }
        else if (!email.matches(CORRECT_EMAIL_REQUIREMENTS)) {
            errorMessage = "Email requirements not fulfilled";
        }
        return errorMessage;
    }


    /**
     * Check if password matches the requirements
     *
     * @param password A password to check
     * @return null if all OK, error message on error
     */
    private String checkPasswordRequirements(String password) {
        String errorMessage = null;
        if (password == null || password.isEmpty()) {
            errorMessage = "Password can't be empty";
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            errorMessage = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters";
        }
        else if(!password.matches(MIN_ONE_CAPITALLETTER) || !password.matches(MIN_ONE_SMALLLETTER) || !password.matches(MIN_ONE_NUMBER)) {
            errorMessage = "Password must contain at least one capital letter, one small letter and on number";
        }
        return errorMessage;
    }

    /**
     * Creates a new user.
     * Also creates a portfolio for the user.
     * @param email the mail you want for the user.
     * @param password the password you want for the user.
     */
    private void createUser(String email, String password, Company company) {
        Role userRole = roleRepository.findOneByName("ROLE_USER");
        if (userRole != null) {
            User user = new User(email, createHash(password), company);
            user.addRole(userRole);
            userRepository.save(user);
        }
    }

    /**
     * Create a secure hash of a password.
     *
     * @param password Plaintext password
     * @return BCrypt hash, with random salt
     */
    private String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Returns all user based on a company
     *
     * @param company the company that you want user from
     * @return list of user based on company
     */
    public List<User> findAllUserByCompany(Company company) {
        List<User> users = this.userRepository.findAllByCompany(company);
        return users;
    }

    /**
     * Return user based on user id.
     * @param id of the user you want to find.
     * @return user.
     */
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Return a User DTO based on a user id
     * @param id of the user that you want to return user DTO of
     * @return User DTO
     */
    public UserDTO findUserDTOById(Long id) {
        User user = findById(id);
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getCompany());
        return userDTO;
    }
}
