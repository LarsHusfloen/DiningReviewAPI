package com.portfolio.diningreviewapi.repositories;

import com.portfolio.diningreviewapi.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

}