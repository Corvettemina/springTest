package com.example.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.CrDateTime;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public ArrayList<Object> greeting(@RequestParam(value = "date", defaultValue = "default") String date) {
        ArrayList<Object> test = new ArrayList<Object>();

        if (date.equals("default")) {
            test.add(new Greeting(counter.incrementAndGet(), (setDate())));

        } else {
            String[] dateArray = date.split("-");
            String month = Integer.toString(Integer.valueOf(dateArray[1]) - 1);
            String currentDate = setDate(dateArray[0], month, dateArray[2]);

            test.add(new Greeting(counter.incrementAndGet(), (currentDate)));
        }

        test.add(new StandardDoxologies());
        return test;
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
