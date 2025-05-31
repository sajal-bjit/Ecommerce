package com.group3.ECommerce.search.productListing.service.impl;

import com.group3.ECommerce.exception.ProductNotFoundException;
import com.group3.ECommerce.productListing.dto.ProductResponseDto;
import com.group3.ECommerce.productListing.model.Product;
import com.group3.ECommerce.productListing.repository.ProductRepository;
import com.group3.ECommerce.productListing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ProductService that handles product search operations.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductResponseDto findProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        
        return mapToDto(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductResponseDto> findProductsByName(String productName, boolean exactMatch) {
        List<Product> products;
        
        if (exactMatch) {
            products = productRepository.findByProductName(productName);
        } else {
            products = productRepository.findByProductNameContainingIgnoreCase(productName);
        }
        
        if (products.isEmpty()) {
            throw new ProductNotFoundException(productName);
        }
        
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps Product entity to ProductResponseDto
     * 
     * @param product the product entity to map
     * @return mapped product DTO
     */
    private ProductResponseDto mapToDto(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .active(product.isActive())
                .build();
    }
}
