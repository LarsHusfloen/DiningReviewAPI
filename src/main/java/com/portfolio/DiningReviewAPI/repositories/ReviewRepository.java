package com.portfolio.DiningReviewAPI.repositories;

import com.portfolio.DiningReviewAPI.entity.Review;
import com.portfolio.DiningReviewAPI.enums.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByStatus(ReviewStatus status);

    List<Review> findByRestaurantIdAndStatus(Long id, ReviewStatus status);
}
