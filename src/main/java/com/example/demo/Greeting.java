package com.example.demo;

public class Greeting {
    private final long id;
    private final String date;
    // private final ArrayList<String> doxologies;

    public Greeting(long id, String date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

}
