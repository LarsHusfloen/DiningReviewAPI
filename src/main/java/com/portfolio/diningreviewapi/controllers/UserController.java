package com.portfolio.diningreviewapi.controllers;

import com.portfolio.diningreviewapi.entity.User1;
import com.portfolio.diningreviewapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User1 user1) {
        validateUser(user1);
        this.userRepository.save(user1);
    }

    @GetMapping("/{name}")
    public User1 getUsersByName(@PathVariable("name") String name) {
        validateName(name);

        User1 existingUser1 = userExists(name);
        existingUser1.setId(null);

        return existingUser1;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInfo(@PathVariable("name") String name, @RequestBody User1 user1) {
        validateName(name);

        User1 existingUser1 = userExists(name);

        copyUserInfoFrom(user1, existingUser1);
        this.userRepository.save(existingUser1);
    }

    private User1 userExists(String name) {
        Optional<User1> optionalExistingUser = this.userRepository.findByName(name);
        if (optionalExistingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalExistingUser.get();
    }

    private void validateUser(User1 user1) {
        validateName(user1.getName());

        Optional<User1> existingUser = this.userRepository.findByName(user1.getName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void copyUserInfoFrom(User1 user1, User1 existingUser1) {
        if (ObjectUtils.isEmpty(user1.getName())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (!ObjectUtils.isEmpty(user1.getCity())) {
            existingUser1.setCity(user1.getCity());
        }
        if (!ObjectUtils.isEmpty(user1.getState())) {
            existingUser1.setState(user1.getState());
        }
        if (!ObjectUtils.isEmpty(user1.getZipcode())) {
            existingUser1.setZipcode(user1.getZipcode());
        }
        if (!ObjectUtils.isEmpty(user1.getPeanutAllergies())) {
            existingUser1.setPeanutAllergies(user1.getPeanutAllergies());
        }
        if (!ObjectUtils.isEmpty(user1.getEggAllergies())) {
            existingUser1.setEggAllergies(user1.getEggAllergies());
        }
        if (!ObjectUtils.isEmpty(user1.getDairyAllergies())) {
            existingUser1.setDairyAllergies(user1.getDairyAllergies());
        }
    }
}
