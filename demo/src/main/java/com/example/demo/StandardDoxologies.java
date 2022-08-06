package com.example.demo;

import java.util.ArrayList;

public class StandardDoxologies {
    private final ArrayList<String> doxologies;

    public StandardDoxologies() {
        this.doxologies = getStandardDoxologies();
    }

    public ArrayList<String> getStandardDoxologies() {
        ArrayList<String> returning = new ArrayList<String>();

        returning.add("mary");
        returning.add("mark");
        return returning;
    }

}
