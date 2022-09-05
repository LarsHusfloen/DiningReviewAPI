package com.portfolio.diningreviewapi.repositories;

import com.portfolio.diningreviewapi.entity.User1;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User1, Long> {

    Optional<User1> findByName(String name);

}
