package com.portfolio.DiningReviewAPI.repositories;

import com.portfolio.DiningReviewAPI.entity.Review;
import com.portfolio.DiningReviewAPI.enums.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    public List<Review> findByStatus(ReviewStatus status);
    public List<Review> findByRestaurantIdAndStatus(Long id, ReviewStatus status);
}
