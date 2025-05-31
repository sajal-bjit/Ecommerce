package com.group3.ECommerce.search.productListing.service;

import com.group3.ECommerce.productListing.dto.ProductResponseDto;
import java.util.List;

/**
 * Service interface defining product search operations.
 */
public interface ProductService {
    
    /**
     * Find a product by its ID
     * 
     * @param productId the ID of the product to find
     * @return the found product as DTO
     * @throws com.group3.ECommerce.exception.ProductNotFoundException if product not found
     */
    ProductResponseDto findProductById(Long productId);
    
    /**
     * Find products by exact or partial name match
     * 
     * @param productName the name to search for
     * @param exactMatch if true, searches for exact match, otherwise partial match
     * @return list of matching products as DTOs
     * @throws com.group3.ECommerce.exception.ProductNotFoundException if no products found
     */
    List<ProductResponseDto> findProductsByName(String productName, boolean exactMatch);
}
