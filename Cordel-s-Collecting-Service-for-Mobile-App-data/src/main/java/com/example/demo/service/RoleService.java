package com.example.demo.service;

import com.example.demo.models.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing {@link Role} entities.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Retrieves all roles.
     *
     * @return A list of {@link Role} objects.
     */
    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }
}
