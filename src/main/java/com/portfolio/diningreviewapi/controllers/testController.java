package com.portfolio.diningreviewapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }
}
