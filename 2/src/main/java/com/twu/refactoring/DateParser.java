package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.YEAR, getYear());
        calendar.set(Calendar.MONTH, getMonth() - 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), getDate(), getHour(), getMinute(), 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getMinute() {
        int minute = 0;
        if (dateAndTimeString.charAt(11) == 'Z') {
            return 0;
        }
        try {
            String minuteString = dateAndTimeString.substring(14, 16);
            minute = getTimeUnitInteger(minuteString, "Minute");
        } catch (StringIndexOutOfBoundsException e) {
            checkTimeUnitStringIllegal("Minute", 2);
        }
        checkTime("Minute", minute, 0, 59);
        return minute;

    }

    private int getHour() {
        int hour = 0;
        if (dateAndTimeString.charAt(11) == 'Z') {
            return 0;
        }
        try {
            String hourString = dateAndTimeString.substring(11, 13);
            hour = getTimeUnitInteger(hourString, "Hour");
        } catch (StringIndexOutOfBoundsException e) {
            checkTimeUnitStringIllegal("Hour", 2);
        }
        checkTime("Hour", hour, 0, 23);
        return hour;

    }

    private int getDate() {
        int date = 0;
        try {
            String dateString = dateAndTimeString.substring(8, 10);
            date = getTimeUnitInteger(dateString, "Date");
        } catch (StringIndexOutOfBoundsException e) {
            checkTimeUnitStringIllegal("Date", 2);
        }
        checkTime("Date", date, 1, 31);
        return date;
    }

    private int getMonth() {
        int month = 0;
        try {
            String monthString = dateAndTimeString.substring(5, 7);
            month = getTimeUnitInteger(monthString, "Month");
        } catch (StringIndexOutOfBoundsException e) {
            checkTimeUnitStringIllegal("Month", 2);
        }
        checkTime("Month", month, 1, 12);
        return month;
    }

    private int getYear() {
        int year = 0;
        try {
            String yearString = dateAndTimeString.substring(0, 4);
            year = getTimeUnitInteger(yearString, "Year");
        } catch (StringIndexOutOfBoundsException e) {
            checkTimeUnitStringIllegal("Year", 4);
        }
        checkTime("Year", year, 2000, 2012);
        return year;
    }

    private int getTimeUnitInteger(String dateString , String timeUnit) {
        if (dateString.matches("^[0-9]*$")) {
            return Integer.parseInt(dateString);
        }
        throw new IllegalArgumentException(timeUnit + " is not an integer");
    }

    private void checkTimeUnitStringIllegal(String timeUnit, int characterNumber) {
        throw new IllegalArgumentException(timeUnit + " string is less than " + characterNumber + " characters");
    }

    private void checkTime(String timeUnit, int time, int start, int end) {
        if (time < start || time > end) {
            throw new IllegalArgumentException(timeUnit + " cannot be less than " + start + " or more than " + end);
        }
    }
}
