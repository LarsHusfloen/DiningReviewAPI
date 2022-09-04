package com.portfolio.DiningReviewAPI.repositories;

import com.portfolio.DiningReviewAPI.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    public Optional<Restaurant> findByNameAndZipcode(String name, String zipcode);
    public List<Restaurant> findByZipcodeAndPeanutNotNullOrderByPeanut(String zipcode);
    public List<Restaurant> findByZipcodeAndDairyNotNullOrderByDairy(String zipcode);
    public List<Restaurant> findByZipcodeAndEggNotNullOrderByEgg(String zipcode);
}
