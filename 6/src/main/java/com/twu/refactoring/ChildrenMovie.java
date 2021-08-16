package com.twu.refactoring;

public class ChildrenMovie implements Movie{

    private final String name;

    public ChildrenMovie(String name) {
        this.name = name;
    }

    @Override
    public double getAmount(int rentDays) {
        if (rentDays > 3) {
            return 1.5 + (rentDays - 3) * 1.5;
        } else {
            return 1.5;
        }
    }

    @Override
    public String getTitle() {
        return name;
    }
}
