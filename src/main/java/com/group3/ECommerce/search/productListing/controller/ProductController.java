package com.group3.ECommerce.search.productListing.controller;

import com.group3.ECommerce.common.ApiResponse;
import com.group3.ECommerce.productListing.dto.ProductResponseDto;
import com.group3.ECommerce.productListing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for product search operations.
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * Endpoint to find product by ID
     * 
     * @param productId ID of the product to find
     * @return API response with found product details
     */
    @GetMapping("/find/{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> findProductById(@PathVariable Long productId) {
        ProductResponseDto product = productService.findProductById(productId);
        
        ApiResponse<ProductResponseDto> response = ApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product found successfully")
                .data(product)
                .build();
                
        return ResponseEntity.ok(response);
    }
    
    /**
     * Endpoint to find products by name (exact or partial match)
     * 
     * @param productName name to search for
     * @param exact query parameter to determine exact or partial matching
     * @return API response with list of found products
     */
    @GetMapping("/find")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> findProductsByName(
            @RequestParam String productName,
            @RequestParam(defaultValue = "false") boolean exact) {
            
        List<ProductResponseDto> products = productService.findProductsByName(productName, exact);
        
        ApiResponse<List<ProductResponseDto>> response = ApiResponse.<List<ProductResponseDto>>builder()
                .success(true)
                .message("Products found successfully")
                .data(products)
                .build();
                
        return ResponseEntity.ok(response);
    }
}
