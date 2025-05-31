package com.group3.ECommerce.controller;

import com.group3.ECommerce.model.ERole;
import com.group3.ECommerce.model.Role;
import com.group3.ECommerce.model.User;
import com.group3.ECommerce.repository.RoleRepository;
import com.group3.ECommerce.repository.UserRepository;
import com.group3.ECommerce.security.jwt.JwtUtils;
import com.group3.ECommerce.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    private String userToken;
    private String adminToken;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        
        // Ensure roles exist
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(ERole.ROLE_USER);
                    return roleRepository.save(role);
                });
        
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(ERole.ROLE_ADMIN);
                    return roleRepository.save(role);
                });
        
        // Create test user with USER role
        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword(passwordEncoder.encode("password"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        testUser.setRoles(userRoles);
        userRepository.save(testUser);
        
        // Create admin user with ADMIN role
        User adminUser = new User();
        adminUser.setUsername("adminuser");
        adminUser.setEmail("adminuser@example.com");
        adminUser.setPassword(passwordEncoder.encode("password"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(userRole);
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);
        
        // Generate tokens
        userToken = generateTokenForUser("testuser", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        adminToken = generateTokenForUser("adminuser", List.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    private String generateTokenForUser(String username, List<SimpleGrantedAuthority> authorities) {
        UserDetailsImpl userDetails = new UserDetailsImpl(1L, username, username + "@example.com", "password", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return jwtUtils.generateJwtToken(authentication);
    }

    @Test
    void accessPublicEndpoint_WithoutAuthentication_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/test/all"))
                .andExpect(status().isOk())
                .andExpect(content().string("Public Content."));
    }

    @Test
    void accessUserEndpoint_WithUserRole_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/test/user")
                .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isOk())
                .andExpect(content().string("User Content."));
    }

    @Test
    void accessUserEndpoint_WithoutAuthentication_ReturnsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void accessAdminEndpoint_WithAdminRole_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/test/admin")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin Board."));
    }

    @Test
    void accessAdminEndpoint_WithUserRole_ReturnsForbidden() throws Exception {
        mockMvc.perform(get("/api/test/admin")
                .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }
}