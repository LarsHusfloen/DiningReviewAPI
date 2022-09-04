package com.portfolio.diningreviewapi.controllers;

import com.portfolio.diningreviewapi.entity.User;
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
    public void addUser(@RequestBody User user) {
        validateUser(user);
        this.userRepository.save(user);
    }

    @GetMapping("/{name}")
    public User getUsersByName(@PathVariable("name") String name) {
        validateName(name);

        User existingUser = userExists(name);
        existingUser.setId(null);

        return existingUser;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInfo(@PathVariable("name") String name, @RequestBody User user) {
        validateName(name);

        User existingUser = userExists(name);

        copyUserInfoFrom(user, existingUser);
        this.userRepository.save(existingUser);
    }

    private User userExists(String name) {
        Optional<User> optionalExistingUser = this.userRepository.findByName(name);
        if (optionalExistingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalExistingUser.get();
    }

    private void validateUser(User user) {
        validateName(user.getName());

        Optional<User> existingUser = this.userRepository.findByName(user.getName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void copyUserInfoFrom(User user, User existingUser) {
        if (ObjectUtils.isEmpty(user.getName())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (!ObjectUtils.isEmpty(user.getCity())) {
            existingUser.setCity(user.getCity());
        }
        if (!ObjectUtils.isEmpty(user.getState())) {
            existingUser.setState(user.getState());
        }
        if (!ObjectUtils.isEmpty(user.getZipcode())) {
            existingUser.setZipcode(user.getZipcode());
        }
        if (!ObjectUtils.isEmpty(user.getPeanutAllergies())) {
            existingUser.setPeanutAllergies(user.getPeanutAllergies());
        }
        if (!ObjectUtils.isEmpty(user.getEggAllergies())) {
            existingUser.setEggAllergies(user.getEggAllergies());
        }
        if (!ObjectUtils.isEmpty(user.getDairyAllergies())) {
            existingUser.setDairyAllergies(user.getDairyAllergies());
        }
    }
}
