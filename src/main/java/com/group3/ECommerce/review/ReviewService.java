package com.group3.ECommerce.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(ReviewRequestDTO requestDTO, Long userId) {
        log.info("Creating review for product {} by user {}", requestDTO.getProductId(), userId);

        // Check for existing review
        if (reviewRepository.existsByUserIdAndProductId(userId, requestDTO.getProductId())) {
            log.warn("User {} has already reviewed product {}", userId, requestDTO.getProductId());
            throw new DuplicateReviewException("You have already reviewed this product");
        }

        // Create and save review
        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(requestDTO.getProductId());
        review.setRating(requestDTO.getRating());
        review.setComment(requestDTO.getComment());

        Review savedReview = reviewRepository.save(review);
        log.info("Review created successfully with ID: {}", savedReview.getId());

        return savedReview;
    }

    public List<Review> getReviewsByProductId(Long productId) {
        log.info("Fetching reviews for product {}", productId);
        List<Review> reviews = reviewRepository.findByProductId(productId);
        log.info("Found {} reviews for product {}", reviews.size(), productId);
        return reviews;
    }
}