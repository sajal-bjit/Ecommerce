package com.group3.ECommerce.service.impl;

import com.group3.ECommerce.productListing.dto.ProductRequest;
import com.group3.ECommerce.productListing.dto.ProductResponse;
import com.group3.ECommerce.productListing.exception.ProductException;
import com.group3.ECommerce.productListing.model.Product;
import com.group3.ECommerce.productListing.repository.ProductRepository;
import com.group3.ECommerce.productListing.service.AuditLogService;
import com.group3.ECommerce.productListing.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final AuditLogService auditLogService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, AuditLogService auditLogService) {
        this.productRepository = productRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, String username) {
        // Validate required fields
        validateProductRequest(productRequest);

        // Check if SKU already exists
        if (productRepository.existsBySku(productRequest.getSku())) {
            throw new ProductException("Product with SKU " + productRequest.getSku() + " already exists");
        }

        // Build product entity
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .category(productRequest.getCategory())
                .sku(productRequest.getSku())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .active(true)
                .createdAt(LocalDateTime.now())
                .createdBy(username)
                .build();

        // Save product
        Product savedProduct = productRepository.save(product);
        logger.info("Product created with ID: {}", savedProduct.getProductId());

        // Record audit log
        auditLogService.logProductCreation(savedProduct, username);

        // Convert to response DTO
        return mapToProductResponse(savedProduct);
    }

    private void validateProductRequest(ProductRequest productRequest) {
        if (productRequest.getProductName() == null || productRequest.getProductName().trim().isEmpty()) {
            throw new ProductException("Product name is required");
        }
        if (productRequest.getDescription() == null || productRequest.getDescription().trim().isEmpty()) {
            throw new ProductException("Product description is required");
        }
        if (productRequest.getCategory() == null || productRequest.getCategory().trim().isEmpty()) {
            throw new ProductException("Product category is required");
        }
        if (productRequest.getSku() == null || productRequest.getSku().trim().isEmpty()) {
            throw new ProductException("Product SKU is required");
        }
        if (productRequest.getPrice() == null || productRequest.getPrice().doubleValue() <= 0) {
            throw new ProductException("Valid product price is required");
        }
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with ID: " + id));
        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductException("Product not found with SKU: " + sku));
        return mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .category(product.getCategory())
                .sku(product.getSku())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .build();
    }
}