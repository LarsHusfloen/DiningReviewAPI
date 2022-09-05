package com.portfolio.diningreviewapi.controllers;

import com.portfolio.diningreviewapi.entity.Restaurant;
import com.portfolio.diningreviewapi.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        validateNewRestaurant(restaurant);

        return this.restaurantRepository.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/search")
    public Iterable<Restaurant> searchRestaurants(@RequestParam String zipcode, @RequestParam String allergy) {
        validateZipCode(zipcode);

        Iterable<Restaurant> restaurants = Collections.emptyList();
        if ("peanut".equalsIgnoreCase(allergy)) {
            restaurants = restaurantRepository.findByZipcodeAndPeanutNotNullOrderByPeanut(zipcode);
        } else if ("dairy".equalsIgnoreCase(allergy)) {
            restaurants = restaurantRepository.findByZipcodeAndDairyNotNullOrderByDairy(zipcode);
        } else if ("egg".equalsIgnoreCase(allergy)) {
            restaurants = restaurantRepository.findByZipcodeAndEggNotNullOrderByEgg(zipcode);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return restaurants;
    }

    private void validateNewRestaurant(Restaurant restaurant) {
        if (ObjectUtils.isEmpty(restaurant.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        validateZipCode(restaurant.getZipcode());

        Optional<Restaurant>
                existingRestaurant = restaurantRepository.findByNameAndZipcode(restaurant.getName(), restaurant.getZipcode());
        if (existingRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateZipCode(String zipcode) {
        Pattern zipcodePattern = Pattern.compile("\\d{4}");
        if (!zipcodePattern.matcher(zipcode).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
