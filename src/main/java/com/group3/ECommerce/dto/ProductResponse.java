package com.group3.ECommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {
    private Long productId;
    private String productName;
    private String description;
    private String category;
    private String sku;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime createdAt;

    // Default constructor
    public ProductResponse() {
    }

    // Builder method
    public static ProductResponseBuilder builder() {
        return new ProductResponseBuilder();
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Builder class
    public static class ProductResponseBuilder {
        private final ProductResponse response = new ProductResponse();

        public ProductResponseBuilder productId(Long id) {
            response.setProductId(id);
            return this;
        }

        public ProductResponseBuilder productName(String name) {
            response.setProductName(name);
            return this;
        }

        public ProductResponseBuilder description(String description) {
            response.setDescription(description);
            return this;
        }

        public ProductResponseBuilder category(String category) {
            response.setCategory(category);
            return this;
        }

        public ProductResponseBuilder sku(String sku) {
            response.setSku(sku);
            return this;
        }

        public ProductResponseBuilder quantity(Integer quantity) {
            response.setQuantity(quantity);
            return this;
        }

        public ProductResponseBuilder price(BigDecimal price) {
            response.setPrice(price);
            return this;
        }

        public ProductResponseBuilder createdAt(LocalDateTime createdAt) {
            response.setCreatedAt(createdAt);
            return this;
        }

        public ProductResponse build() {
            return response;
        }
    }
}