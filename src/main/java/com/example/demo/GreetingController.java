package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.CSACommunion;
import api.CSALiturgyOfTheWord;
import api.CSALiturgyofTheFaithful;
import api.CSAMatins;
import api.CSAOffering;
import api.CSAVespers;
import api.CrDateTime;
import api.CurrentSeasonAttributes;
import api.CurrentSeasonInterpreter;
import api.OccasionEvaluatorTest;
import api.SesasonEvaluatorTest;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    private CurrentSeasonAttributes csa;
    private CurrentSeasonInterpreter current;
    private static CrDateTime cr;

    @CrossOrigin
    @GetMapping("/greeting")
    public ArrayList<Object> greeting(@RequestParam(value = "date", defaultValue = "default") String date)
            throws IOException {
        ArrayList<Object> output = new ArrayList<Object>();

        if (date.equals("default")) {
            output.add(new Greeting(counter.incrementAndGet(), (cr.asCopticDate().toCopticString())));

        } else {
            String[] dateArray = date.split("-");
            String month = Integer.toString(Integer.valueOf(dateArray[1]) - 1);
            cr = setDate(dateArray[0], month, dateArray[2]);
            OccasionEvaluatorTest oet = new OccasionEvaluatorTest(cr);
            SesasonEvaluatorTest set = new SesasonEvaluatorTest(oet);
            current = new CurrentSeasonInterpreter(set, oet);

            output.add(new Greeting(counter.incrementAndGet(), (cr.asCopticDate().toCopticString())));
        }
        csa = new CurrentSeasonAttributes(current);
        output.add(csa);
        return output;
    }

    public static CrDateTime setDate() {
        Calendar cal = Calendar.getInstance();
        cr = new CrDateTime(cal);
        return cr;
    }

    public static CrDateTime setDate(String year, String month, String day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        cr = new CrDateTime(cal);
        return cr;
    }

    @CrossOrigin
    @GetMapping("/vespers")
    public ArrayList<Object> seasonalVespersDoxo() {
        ArrayList<Object> vespers = new ArrayList<Object>();
        CSAVespers csaVespers = new CSAVespers(current);
        HomeScreen homeScreen = new HomeScreen(current, cr);
        vespers.add(homeScreen);
        vespers.add(csaVespers);
        
        return vespers;
    }

    @CrossOrigin
    @GetMapping("/matins")
    public CSAMatins matins() {
        CSAMatins csaMatins = new CSAMatins(current);
        return csaMatins;
    }

    @CrossOrigin
    @GetMapping("/offering")
    public CSAOffering offering() {
        CSAOffering csaOffering = new CSAOffering(current);
        return csaOffering;
    }

    @CrossOrigin
    @GetMapping("/liturgyOfWord")
    public CSALiturgyOfTheWord liturgyOfWord() {
        CSALiturgyOfTheWord csaliturgyOfWord = new CSALiturgyOfTheWord(current);
        return csaliturgyOfWord;
    }

    @CrossOrigin
    @GetMapping("/liturgyOfFaithful")
    public CSALiturgyofTheFaithful liturgyOfFaithful() {
        CSALiturgyofTheFaithful csaliturgyOFaithful = new CSALiturgyofTheFaithful(current);
        return csaliturgyOFaithful;
    }

    @CrossOrigin
    @GetMapping("/communion")
    public CSACommunion communion() {
        CSACommunion csaCommunion = new CSACommunion(current);
        return csaCommunion;
    }

    @CrossOrigin
    @PostMapping("/date")
    public String date(@RequestParam("date") String date) {
        String[] dateArray = date.split("-");
        String month = Integer.toString(Integer.valueOf(dateArray[1]) - 1);
        cr = setDate(dateArray[0], month, dateArray[2]);
        OccasionEvaluatorTest oet = new OccasionEvaluatorTest(cr);
        SesasonEvaluatorTest set = new SesasonEvaluatorTest(oet);
        current = new CurrentSeasonInterpreter(set, oet);
        // csa = new CurrentSeasonAttributes(current);

        return "Date recived sucussfully: " + date;

    }

}
