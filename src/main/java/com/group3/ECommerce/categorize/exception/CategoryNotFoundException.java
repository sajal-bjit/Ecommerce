package com.group3.ECommerce.categorize.exception;

/**
 * Exception thrown when a category is not found
 */
public class CategoryNotFoundException extends RuntimeException {
    
    public CategoryNotFoundException(String category) {
        super("No products found in category: " + category);
    }
}