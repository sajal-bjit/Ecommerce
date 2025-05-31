package com.group3.ECommerce.categorize.service;

import com.group3.ECommerce.categorize.dtos.ProductDTO;
import com.group3.ECommerce.categorize.exception.CategoryNotFoundException;
import com.group3.ECommerce.categorize.repository.ProductRepository;
import com.group3.ECommerce.productListing.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CategorizeService
 */
@Service
public class CategorizeServiceImpl implements CategorizeService {

    private final ProductRepository productRepository;

    @Autowired
    public CategorizeServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        // Check if category exists before querying for products
        if (!productRepository.existsByCategoryAndActiveTrue(category)) {
            throw new CategoryNotFoundException(category);
        }
        
        // Get all products in the category
        List<Product> products = productRepository.findByCategoryAndActiveTrue(category);
        
        // Map Product entities to ProductDTOs
        return products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a Product entity to ProductDTO
     *
     * @param product the product entity to map
     * @return the mapped ProductDTO
     */
    private ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}