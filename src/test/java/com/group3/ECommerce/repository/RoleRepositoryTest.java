package com.group3.ECommerce.repository;

import com.group3.ECommerce.model.ERole;
import com.group3.ECommerce.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByName_ExistingRole_ReturnsRole() {
        // Arrange
        Role adminRole = new Role();
        adminRole.setName(ERole.ROLE_ADMIN);
        roleRepository.save(adminRole);

        // Act
        Optional<Role> foundRole = roleRepository.findByName(ERole.ROLE_ADMIN);

        // Assert
        assertTrue(foundRole.isPresent());
        assertEquals(ERole.ROLE_ADMIN, foundRole.get().getName());
    }

    @Test
    void findByName_NonExistingRole_ReturnsEmpty() {
        // Clean repository to ensure role doesn't exist
        roleRepository.deleteAll();
        
        // Act
        Optional<Role> foundRole = roleRepository.findByName(ERole.ROLE_GUEST);

        // Assert
        assertTrue(foundRole.isEmpty());
    }
}