package com.group3.ECommerce.review;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class ReviewRequestDTO {
//    @NotNull(message = "Product ID is required")
    private Long productId;

//    @NotNull(message = "Rating is required")
//    @Min(value = 1, message = "Rating must be between 1 and 5")
//    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

//    @Size(max = 1000, message = "Comment must not exceed 1000 characters")
    private String comment;
}