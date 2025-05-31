package com.group3.ECommerce.search.productListing.repository;

import com.group3.ECommerce.productListing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Product entity to handle database operations.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Finds products by exact name match
     * 
     * @param productName the exact product name to search for
     * @return list of products matching the exact name
     */
    List<Product> findByProductName(String productName);
    
    /**
     * Finds products by partial name match, case-insensitive
     * 
     * @param productName the partial product name to search for
     * @return list of products containing the name pattern
     */
    List<Product> findByProductNameContainingIgnoreCase(String productName);
}
