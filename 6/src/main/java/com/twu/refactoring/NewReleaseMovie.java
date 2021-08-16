package com.twu.refactoring;

public class NewReleaseMovie implements Movie{

    private final String name;

    public NewReleaseMovie(String name) {
        this.name = name;
    }

    @Override
    public double getAmount(int rentDays) {
        return rentDays * 3;
    }

    @Override
    public String getTitle() {
        return name;
    }
}
