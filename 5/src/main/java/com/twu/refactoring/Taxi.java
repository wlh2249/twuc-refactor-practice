package com.twu.refactoring;

public class Taxi {
    static final double PEAK_TIME_MULTIPLIER = 1.2;
    static final double OFF_PEAK_MULTIPLIER = 1.0;
    static final int RATE_CHANGE_DISTANCE = 10;
    static final int PRE_RATE_CHANGE_NON_AC_RATE = 15;
    static final int POST_RATE_CHANGE_NON_AC_RATE = 12;
    static final int PRE_RATE_CHANGE_AC_RATE = 20;
    static final int POST_RATE_CHANGE_AC_RATE = 17;

    private final boolean airConditioned;
    private final int totalKms;
    private final boolean peakTime;

    public Taxi(boolean airConditioned, int totalKms, boolean peakTime) {
        this.airConditioned = airConditioned;
        this.totalKms = totalKms;
        this.peakTime = peakTime;
    }

    public boolean isAirConditioned() {
        return airConditioned;
    }

    public boolean isPeakTime() {
        return peakTime;
    }

    public double getCost() {
        double peakTimeMultiple = isPeakTime() ? PEAK_TIME_MULTIPLIER : OFF_PEAK_MULTIPLIER;
        double totalCost;
        if(isAirConditioned()) {
            totalCost = getACCost() * peakTimeMultiple;
        } else {
            totalCost = getNonACCost() * peakTimeMultiple;
        }
        return totalCost;
    }

    private double getACCost() {
        return Math.min(RATE_CHANGE_DISTANCE, totalKms) * PRE_RATE_CHANGE_AC_RATE
                + Math.max(0, totalKms - RATE_CHANGE_DISTANCE) * POST_RATE_CHANGE_AC_RATE ;
    }

    private double getNonACCost() {
        return Math.min(RATE_CHANGE_DISTANCE, totalKms) * PRE_RATE_CHANGE_NON_AC_RATE
                + Math.max(0, totalKms - RATE_CHANGE_DISTANCE) * POST_RATE_CHANGE_NON_AC_RATE;
    }
}
