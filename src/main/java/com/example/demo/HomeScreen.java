package com.example.demo;

import java.util.Calendar;

import api.CrDateTime;
import api.CurrentSeasonInterpreter;
import api.OccasionEvaluatorTest;
import api.SesasonEvaluatorTest;

public class HomeScreen {

    private final String copticDate;
    private final String Sunday;
    private final String Ocassion;
    private final String Season;

    public HomeScreen() {
        Calendar cal = Calendar.getInstance();
        CrDateTime cr = new CrDateTime(cal);
        OccasionEvaluatorTest oet = new OccasionEvaluatorTest(cr);
        SesasonEvaluatorTest set = new SesasonEvaluatorTest(oet);
        CurrentSeasonInterpreter current = new CurrentSeasonInterpreter(set, oet);

        this.Sunday = current.Sunday;
        this.Season = current.Season;
        this.Ocassion = current.Ocassion;
        this.copticDate = cr.asCopticDate().toCopticString();

    }

    public HomeScreen(CurrentSeasonInterpreter current, CrDateTime cr) {

        this.Sunday = current.Sunday;
        this.Season = current.Season;
        this.Ocassion = current.Ocassion;
        this.copticDate = cr.asCopticDate().toCopticString();

    }

    public String getSunday() {
        return Sunday;
    }

    public String getOcassion() {
        return Ocassion;
    }

    public String getSeason() {
        return Season;
    }

    public String getCopticDate() {
        return copticDate;
    }

}
