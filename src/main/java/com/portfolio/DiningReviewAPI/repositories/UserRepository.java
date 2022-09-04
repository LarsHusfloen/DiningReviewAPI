package com.portfolio.DiningReviewAPI.repositories;

import com.portfolio.DiningReviewAPI.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

}
