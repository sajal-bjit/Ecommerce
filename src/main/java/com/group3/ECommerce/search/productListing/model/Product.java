package com.group3.ECommerce.productListing.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/** * Entity class representing a product in the e-commerce system. */
@Entity@Table(name = "products")
@Data@NoArgsConstructor@AllArgsConstructor
@Builderclass Product {    
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long productId;    
    
    private String productName;    
    
    private String category;    
    
    private Integer quantity;    
    
    private BigDecimal price;    
    
    private boolean active = true;
}