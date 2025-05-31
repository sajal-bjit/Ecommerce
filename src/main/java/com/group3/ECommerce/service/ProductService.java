package com.group3.ECommerce.service;

import com.group3.ECommerce.productListing.dto.ProductRequest;
import com.group3.ECommerce.productListing.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest, String username);
    ProductResponse getProductById(Long id);
    ProductResponse getProductBySku(String sku);
    List<ProductResponse> getAllProducts();
}