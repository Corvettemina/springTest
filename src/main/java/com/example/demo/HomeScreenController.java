package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeScreenController {

    @CrossOrigin
    @GetMapping("/home")
    public HomeScreen homescreen(@RequestParam(value = "date", defaultValue = "default") String date) {
        if (date.equals("default")) {
            return new HomeScreen();
        }else{
            return new HomeScreen(date);
        }

    }
}