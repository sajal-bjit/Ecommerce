package com.group3.ECommerce.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a product is not found in the system.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
    }
    
    public ProductNotFoundException(String productName) {
        super("No products found with name: " + productName);
    }
}
