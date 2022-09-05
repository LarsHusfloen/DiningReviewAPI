package com.portfolio.diningreviewapi.controllers;

import com.portfolio.diningreviewapi.entity.AdminReviewAction;
import com.portfolio.diningreviewapi.entity.Restaurant;
import com.portfolio.diningreviewapi.entity.Review;
import com.portfolio.diningreviewapi.enums.ReviewStatus;
import com.portfolio.diningreviewapi.repositories.RestaurantRepository;
import com.portfolio.diningreviewapi.repositories.ReviewRepository;
import com.portfolio.diningreviewapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public AdminController(ReviewRepository reviewRepository,
                           UserRepository userRepository,
                           RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public List<Review> getReviewsByStatus(@RequestParam String review_status) {
        ReviewStatus reviewStatus = ReviewStatus.PENDING;
        try {
            reviewStatus = ReviewStatus.valueOf(review_status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return reviewRepository.findByStatus(reviewStatus);
    }

    @PutMapping("/reviews/{reviewId}")
    public void performReviewAction(@PathVariable Long reviewId, @RequestBody AdminReviewAction adminReviewAction) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Review review = optionalReview.get();

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (Boolean.TRUE.equals(adminReviewAction.getAcceptReview())) {
            review.setStatus(ReviewStatus.APPROVED);
        } else {
            review.setStatus(ReviewStatus.REJECTED);
        }

        reviewRepository.save(review);
        updateRestaurantReviewScores(optionalRestaurant.get());
    }

    private void updateRestaurantReviewScores(Restaurant restaurant) {
        List<Review> reviews = reviewRepository.findByRestaurantIdAndStatus(restaurant.getId(), ReviewStatus.APPROVED);
        if (reviews.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        int peanutSum = 0;
        int peanutCount = 0;
        int dairySum = 0;
        int dairyCount = 0;
        int eggSum = 0;
        int eggCount = 0;
        for (Review r : reviews) {
            if (!ObjectUtils.isEmpty(r.getPeanut())) {
                peanutSum += r.getPeanut();
                peanutCount++;
            }
            if (!ObjectUtils.isEmpty(r.getDairy())) {
                dairySum += r.getDairy();
                dairyCount++;
            }
            if (!ObjectUtils.isEmpty(r.getEgg())) {
                eggSum += r.getEgg();
                eggCount++;
            }
        }

        int totalCount = peanutCount + dairyCount + eggCount;
        int totalSum = peanutSum + dairySum + eggSum;

        float overallScore = (float) totalSum / totalCount;
        restaurant.setAverage(Integer.valueOf(decimalFormat.format(overallScore)));

        if (peanutCount > 0) {
            float peanutScore = (float) peanutSum / peanutCount;
            restaurant.setPeanut(Integer.valueOf(decimalFormat.format(peanutScore)));
        }

        if (dairyCount > 0) {
            float dairyScore = (float) dairySum / dairyCount;
            restaurant.setDairy(Integer.valueOf(decimalFormat.format(dairyScore)));
        }

        if (eggCount > 0) {
            float eggScore = (float) eggSum / eggCount;
            restaurant.setEgg(Integer.valueOf(decimalFormat.format(eggScore)));
        }

        restaurantRepository.save(restaurant);
    }
}
