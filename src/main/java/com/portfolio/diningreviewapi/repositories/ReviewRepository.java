package com.portfolio.diningreviewapi.repositories;

import com.portfolio.diningreviewapi.entity.Review;
import com.portfolio.diningreviewapi.enums.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByStatus(ReviewStatus status);

    List<Review> findByRestaurantIdAndStatus(Long id, ReviewStatus status);
}
