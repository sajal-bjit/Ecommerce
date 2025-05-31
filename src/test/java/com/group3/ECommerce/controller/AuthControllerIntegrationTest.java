package com.group3.ECommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.ECommerce.model.ERole;
import com.group3.ECommerce.model.Role;
import com.group3.ECommerce.payload.request.LoginRequest;
import com.group3.ECommerce.payload.request.SignupRequest;
import com.group3.ECommerce.repository.RoleRepository;
import com.group3.ECommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        
        // Ensure roles exist
        if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
            Role userRole = new Role();
            userRole.setName(ERole.ROLE_USER);
            roleRepository.save(userRole);
        }
        if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()) {
            Role adminRole = new Role();
            adminRole.setName(ERole.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }
    }

    @Test
    void signupUser_ValidCredentials_ReturnsSuccess() throws Exception {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testuser");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password123");
        signupRequest.setFirstName("Test");
        signupRequest.setLastName("User");
        
        Set<String> roles = new HashSet<>();
        roles.add("user");
        signupRequest.setRole(roles);

        // Act
        ResultActions response = mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)));

        // Assert
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("User registered successfully!")));
        
        assertTrue(userRepository.existsByUsername("testuser"));
    }

    @Test
    void signupUser_DuplicateUsername_ReturnsBadRequest() throws Exception {
        // Arrange - Create a user first
        SignupRequest firstSignup = new SignupRequest();
        firstSignup.setUsername("testuser");
        firstSignup.setEmail("test1@example.com");
        firstSignup.setPassword("password123");
        firstSignup.setFirstName("Test");
        firstSignup.setLastName("User");
        
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstSignup)));
        
        // Try to create another user with the same username
        SignupRequest duplicateUsername = new SignupRequest();
        duplicateUsername.setUsername("testuser");
        duplicateUsername.setEmail("test2@example.com");
        duplicateUsername.setPassword("password123");
        duplicateUsername.setFirstName("Another");
        duplicateUsername.setLastName("User");

        // Act
        ResultActions response = mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicateUsername)));

        // Assert
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Error: Username is already taken!")));
    }

    @Test
    void signinUser_ValidCredentials_ReturnsJwtToken() throws Exception {
        // Arrange - Create a user first
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("loginuser");
        signupRequest.setEmail("login@example.com");
        signupRequest.setPassword("password123");
        signupRequest.setFirstName("Login");
        signupRequest.setLastName("User");
        
        Set<String> roles = new HashSet<>();
        roles.add("user");
        signupRequest.setRole(roles);
        
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)));
        
        // Create login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("loginuser");
        loginRequest.setPassword("password123");

        // Act
        ResultActions response = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));

        // Assert
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username", is("loginuser")))
                .andExpect(jsonPath("$.email", is("login@example.com")));
    }

    @Test
    void signinUser_InvalidCredentials_ReturnsUnauthorized() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("nonexistentuser");
        loginRequest.setPassword("wrongpassword");

        // Act
        ResultActions response = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));

        // Assert
        response.andDo(print())
                .andExpect(status().isUnauthorized());
    }
}