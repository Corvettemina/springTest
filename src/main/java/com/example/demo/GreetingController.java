package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dropbox.core.DbxException;

import api.CrDateTime;
import api.CurrentSeasonAttributes;
import api.CurrentSeasonInterpreter;
import api.OccasionEvaluatorTest;
import api.SesasonEvaluatorTest;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin
    @GetMapping("/greeting")
    public ArrayList<Object> greeting(@RequestParam(value = "date", defaultValue = "default") String date)
            throws IOException {
        ArrayList<Object> output = new ArrayList<Object>();
        CrDateTime cr;
        if (date.equals("default")) {
            cr = setDate();
            output.add(new Greeting(counter.incrementAndGet(), (cr.asCopticDate().toCopticString())));

        } else {
            String[] dateArray = date.split("-");
            String month = Integer.toString(Integer.valueOf(dateArray[1]) - 1);
            cr = setDate(dateArray[0], month, dateArray[2]);

            output.add(new Greeting(counter.incrementAndGet(), (cr.asCopticDate().toCopticString())));
        }

        // test.add(new StandardDoxologies());
        OccasionEvaluatorTest oet = new OccasionEvaluatorTest(cr);
        SesasonEvaluatorTest set = new SesasonEvaluatorTest(oet);
        CurrentSeasonInterpreter current = new CurrentSeasonInterpreter(set, oet);

        CurrentSeasonAttributes csa;
        try {
            csa = new CurrentSeasonAttributes(current);
        } catch (DbxException e) {
            csa = null;
            e.printStackTrace();
        }
        output.add(csa);
        return output;
    }

    public static CrDateTime setDate() {
        Calendar cal = Calendar.getInstance();
        CrDateTime cr = new CrDateTime(cal);
        return cr;
    }

    public static CrDateTime setDate(String year, String month, String day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        CrDateTime cr = new CrDateTime(cal);
        return cr;
    }

}
