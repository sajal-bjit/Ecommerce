package com.group3.ECommerce.controller;

import com.group3.ECommerce.productListing.dto.ProductRequest;
import com.group3.ECommerce.productListing.dto.ProductResponse;
import com.group3.ECommerce.productListing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        // For simplicity, using a hardcoded username
        String username = "admin";
        ProductResponse createdProduct = productService.createProduct(productRequest, username);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
}