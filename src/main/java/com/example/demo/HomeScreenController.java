package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeScreenController {

    @CrossOrigin
    @GetMapping("/home")
    public HomeScreen homescreen() {

        return new HomeScreen();

    }
}