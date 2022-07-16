package com.example.demo;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.CrDateTime;

@RestController
public class GreetingController {
    private static final String template = setDate() + "  %s!";
    private final AtomicLong counter = new AtomicLong();
    private String test = setDate();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    public static String setDate() {
        Calendar cal = Calendar.getInstance();
        //cal.set(2022, 4, 17);
        CrDateTime cr = new CrDateTime(cal);
        return cr.asCopticDate().toCopticString();
    }

}
