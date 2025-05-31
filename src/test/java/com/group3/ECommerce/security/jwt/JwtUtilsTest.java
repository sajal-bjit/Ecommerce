package com.group3.ECommerce.security.jwt;


import com.group3.ECommerce.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilsTest {

    private JwtUtils jwtUtils;
    private UserDetailsImpl userDetails;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "testSecretKey12345678901234567890123456789012");
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 60000); // 1 minute
        
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        userDetails = new UserDetailsImpl(1L, "testuser", "test@example.com", "password", authorities);
        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Test
    void generateJwtToken_ValidAuthentication_ReturnsToken() {
        // Act
        String token = jwtUtils.generateJwtToken(authentication);
        
        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void getUserNameFromJwtToken_ValidToken_ReturnsUsername() {
        // Arrange
        String token = jwtUtils.generateJwtToken(authentication);
        
        // Act
        String username = jwtUtils.getUserNameFromJwtToken(token);
        
        // Assert
        assertEquals("testuser", username);
    }

    @Test
    void validateJwtToken_ValidToken_ReturnsTrue() {
        // Arrange
        String token = jwtUtils.generateJwtToken(authentication);
        
        // Act & Assert
        assertTrue(jwtUtils.validateJwtToken(token));
    }

    @Test
    void validateJwtToken_InvalidToken_ReturnsFalse() {
        // Act & Assert
        assertFalse(jwtUtils.validateJwtToken("invalidToken"));
    }
}