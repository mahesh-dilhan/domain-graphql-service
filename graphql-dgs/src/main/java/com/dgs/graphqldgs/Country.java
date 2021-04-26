package com.dgs.graphqldgs;

public class Country {
    private final String name;
    private final Integer positiveCases;

    public Country(String name, Integer positiveCases) {
        this.name = name;
        this.positiveCases = positiveCases;
    }

    public String getName() {
        return name;
    }

    public Integer getPositiveCases() {
        return positiveCases;
    }
}