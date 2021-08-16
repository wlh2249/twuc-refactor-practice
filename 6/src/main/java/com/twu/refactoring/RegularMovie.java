package com.twu.refactoring;

public class RegularMovie implements Movie{

    private final String name;

    public RegularMovie(String name) {
        this.name = name;
    }

    @Override
    public double getAmount(int rentDays) {
        if (rentDays > 2) {
            return 2 + (rentDays - 2) * 1.5;
        } else {
            return 2;
        }
    }

    @Override
    public String getTitle() {
        return name;
    }
}
