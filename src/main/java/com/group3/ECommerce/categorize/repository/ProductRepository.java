package com.group3.ECommerce.categorize.repository;

import com.group3.ECommerce.productListing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Product entity
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Find all active products by category
     *
     * @param category the category to search for
     * @return list of products in the specified category
     */
    List<Product> findByCategoryAndActiveTrue(String category);
    
    /**
     * Check if products exist for a given category
     *
     * @param category the category to check
     * @return true if products exist, false otherwise
     */
    boolean existsByCategoryAndActiveTrue(String category);
}