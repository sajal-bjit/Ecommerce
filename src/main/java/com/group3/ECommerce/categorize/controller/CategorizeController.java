package com.group3.ECommerce.categorize.controller;

import com.group3.ECommerce.categorize.dtos.ProductDTO;
import com.group3.ECommerce.categorize.dtos.ResponseDTO;
import com.group3.ECommerce.categorize.service.CategorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for product categorization operations
 */
@RestController
@RequestMapping("/api/v1/categorize")
public class CategorizeController {

    private final CategorizeService categorizeService;

    @Autowired
    public CategorizeController(CategorizeService categorizeService) {
        this.categorizeService = categorizeService;
    }

    /**
     * Endpoint to get products by category
     *
     * @param category the category to search for
     * @return ResponseEntity containing list of products in the category
     */
    @GetMapping("/{category}")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getProductsByCategory(@PathVariable String category) {
        List<ProductDTO> products = categorizeService.getProductsByCategory(category);
        
        ResponseDTO<List<ProductDTO>> response = ResponseDTO.success(
                "Products retrieved successfully for category: " + category,
                products
        );
        
        return ResponseEntity.ok(response);
    }
}