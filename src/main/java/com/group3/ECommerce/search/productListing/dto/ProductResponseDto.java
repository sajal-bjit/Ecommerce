package com.group3.ECommerce.search.productListing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Product responses.
 * Separates entity representation from API responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private boolean active;
}
