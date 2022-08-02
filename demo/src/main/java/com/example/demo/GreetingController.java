package com.example.demo;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.CrDateTime;

@RestController
public class GreetingController {

    static int[] test = { 2022, 10, 10 };
    //private static final String template = setDate(test) + "  %s!";
    private final AtomicLong counter = new AtomicLong();
    private final static String nameTest = "testymctest";
    // private String test = setDate();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = nameTest) String name) {
        String test1 = "MINA";

        // return new Greeting(counter.incrementAndGet(), String.format(test1, name));
        String year = name.substring(0, 5);
        String month = name.substring(4, 7);
        String day = name.substring(6);
        String test2 = setDate(year, day, month);
        return new Greeting(counter.incrementAndGet(), (test1 + " " + test2));
    }

    public static String setDate() {
        Calendar cal = Calendar.getInstance();
        // cal.set(2022, 4, 17);
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
