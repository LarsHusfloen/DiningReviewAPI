package com.portfolio.DiningReviewAPI.repositories;

import com.portfolio.DiningReviewAPI.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Optional<Restaurant> findByNameAndZipcode(String name, String zipcode);

    List<Restaurant> findByZipcodeAndPeanutNotNullOrderByPeanut(String zipcode);

    List<Restaurant> findByZipcodeAndDairyNotNullOrderByDairy(String zipcode);

    List<Restaurant> findByZipcodeAndEggNotNullOrderByEgg(String zipcode);
}
