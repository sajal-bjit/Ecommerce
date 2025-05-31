package com.group3.ECommerce.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);

    Optional<Review> findByUserIdAndProductId(Long userId, Long productId);

    List<Review> findByProductId(Long productId);
}