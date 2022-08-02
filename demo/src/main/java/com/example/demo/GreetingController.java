package com.example.demo;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.CrDateTime;

@RestController
public class GreetingController {

    // private static final String template = setDate(test) + " %s!";
    private final AtomicLong counter = new AtomicLong();

    // private String test = setDate();

    @GetMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "default") String name) {

        // System.out.println(name);
        // return new Greeting(counter.incrementAndGet(), String.format(test1, name));
        if (name.equals("default")) {
            return new Greeting(counter.incrementAndGet(), (setDate()));
        } else {
            String year = name.substring(0, 4);
            String month = name.substring(4, 6);
            month = Integer.toString(Integer.valueOf(month) - 1);
            String day = name.substring(6);
            String test2 = setDate(year, month, day);
            return new Greeting(counter.incrementAndGet(), (test2));
        }

    }

    public static String setDate() {
        Calendar cal = Calendar.getInstance();
        CrDateTime cr = new CrDateTime(cal);
        return cr.asCopticDate().toCopticString();
    }

    public static String setDate(String year, String month, String day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        CrDateTime cr = new CrDateTime(cal);
        return cr.asCopticDate().toCopticString();
    }

}
