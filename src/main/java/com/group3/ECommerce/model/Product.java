package com.group3.ECommerce.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String description;
    private String category;

    @Column(unique = true)
    private String sku;

    private Integer quantity;
    private BigDecimal price;
    private boolean active = true;
    private LocalDateTime createdAt;
    private String createdBy;

    // Default constructor
    public Product() {
    }

    // Builder constructor
    public static ProductBuilder builder() {
        return new ProductBuilder();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Builder class
    public static class ProductBuilder {
        private final Product product = new Product();

        public ProductBuilder productId(Long productId) {
            product.setProductId(productId);
            return this;
        }

        public ProductBuilder productName(String productName) {
            product.setProductName(productName);
            return this;
        }

        public ProductBuilder description(String description) {
            product.setDescription(description);
            return this;
        }

        public ProductBuilder category(String category) {
            product.setCategory(category);
            return this;
        }

        public ProductBuilder sku(String sku) {
            product.setSku(sku);
            return this;
        }

        public ProductBuilder quantity(Integer quantity) {
            product.setQuantity(quantity);
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            product.setPrice(price);
            return this;
        }

        public ProductBuilder active(boolean active) {
            product.setActive(active);
            return this;
        }

        public ProductBuilder createdAt(LocalDateTime createdAt) {
            product.setCreatedAt(createdAt);
            return this;
        }

        public ProductBuilder createdBy(String createdBy) {
            product.setCreatedBy(createdBy);
            return this;
        }

        public Product build() {
            return product;
        }
    }
}