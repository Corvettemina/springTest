package com.example.demo;

import java.util.ArrayList;

public class StandardDoxologies {
    public int int1;
    public String string1;

    public StandardDoxologies() {
        this.int1 = 1;
        this.string1 = "Hey";
        getStandardDoxologies();

    }

    public ArrayList<String> getStandardDoxologies() {
        ArrayList<String> returning = new ArrayList<String>();

        returning.add("mary");
        returning.add("mark");
        return returning;
    }

}
