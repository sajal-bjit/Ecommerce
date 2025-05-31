package com.group3.ECommerce.dto;

import java.math.BigDecimal;

public class ProductRequest {
    private String productName;
    private String description;
    private String category;
    private String sku;
    private Integer quantity;
    private BigDecimal price;

    // Default constructor
    public ProductRequest() {
    }

    // All-args constructor
    public ProductRequest(String productName, String description, String category,
                          String sku, Integer quantity, BigDecimal price) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    // Builder method
    public static ProductRequestBuilder builder() {
        return new ProductRequestBuilder();
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Builder class
    public static class ProductRequestBuilder {
        private final ProductRequest request = new ProductRequest();

        public ProductRequestBuilder productName(String productName) {
            request.setProductName(productName);
            return this;
        }

        public ProductRequestBuilder description(String description) {
            request.setDescription(description);
            return this;
        }

        public ProductRequestBuilder category(String category) {
            request.setCategory(category);
            return this;
        }

        public ProductRequestBuilder sku(String sku) {
            request.setSku(sku);
            return this;
        }

        public ProductRequestBuilder quantity(Integer quantity) {
            request.setQuantity(quantity);
            return this;
        }

        public ProductRequestBuilder price(BigDecimal price) {
            request.setPrice(price);
            return this;
        }

        public ProductRequest build() {
            return request;
        }
    }
}