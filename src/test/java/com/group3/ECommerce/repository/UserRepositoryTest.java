package com.group3.ECommerce.repository;

import com.group3.ECommerce.model.ERole;
import com.group3.ECommerce.model.Role;
import com.group3.ECommerce.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByUsername_ExistingUser_ReturnsUser() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Act
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void findByUsername_NonExistingUser_ReturnsEmpty() {
        // Act
        Optional<User> foundUser = userRepository.findByUsername("nonexistentuser");

        // Assert
        assertTrue(foundUser.isEmpty());
    }

    @Test
    void existsByUsername_ExistingUser_ReturnsTrue() {
        // Arrange
        User user = new User();
        user.setUsername("uniqueusername");
        user.setEmail("unique@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByUsername("uniqueusername");

        // Assert
        assertTrue(exists);
    }

    @Test
    void existsByUsername_NonExistingUser_ReturnsFalse() {
        // Act
        boolean exists = userRepository.existsByUsername("nonexistentuser");

        // Assert
        assertFalse(exists);
    }

    @Test
    void existsByEmail_ExistingEmail_ReturnsTrue() {
        // Arrange
        User user = new User();
        user.setUsername("emailuser");
        user.setEmail("unique@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByEmail("unique@example.com");

        // Assert
        assertTrue(exists);
    }

    @Test
    void existsByEmail_NonExistingEmail_ReturnsFalse() {
        // Act
        boolean exists = userRepository.existsByEmail("nonexistent@example.com");

        // Assert
        assertFalse(exists);
    }
}