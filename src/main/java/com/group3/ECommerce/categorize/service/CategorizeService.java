package com.group3.ECommerce.categorize.service;

import com.group3.ECommerce.categorize.dtos.ProductDTO;
import java.util.List;

/**
 * Service interface for product categorization operations
 */
public interface CategorizeService {
    
    /**
     * Retrieves all products in a specific category
     *
     * @param category the category to search for
     * @return list of products in the specified category
     * @throws CategoryNotFoundException if no products are found in the category
     */
    List<ProductDTO> getProductsByCategory(String category);
}