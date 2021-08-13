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
        int minute;
        if (dateAndTimeString.charAt(11) == 'Z') {
            return 0;
        }
        try {
            String minuteString = dateAndTimeString.substring(14, 16);
            minute = getTimeUnitInteger(minuteString, "Minute");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Minute string is less than 2 characters");
        }
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException("Minute cannot be less than 0 or more than 59");
        return minute;

    }

    private int getHour() {
        int hour;
        if (dateAndTimeString.charAt(11) == 'Z') {
            return 0;
        }
        try {
            String hourString = dateAndTimeString.substring(11, 13);
            hour = getTimeUnitInteger(hourString, "Hour");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Hour string is less than 2 characters");
        }
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException("Hour cannot be less than 0 or more than 23");
        return hour;

    }

    private int getDate() {
        int date;
        try {
            String dateString = dateAndTimeString.substring(8, 10);
            date = getTimeUnitInteger(dateString, "Date");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Date string is less than 2 characters");
        }
        if (date < 1 || date > 31)
            throw new IllegalArgumentException("Date cannot be less than 1 or more than 31");
        return date;
    }

    private int getMonth() {
        int month;
        try {
            String monthString = dateAndTimeString.substring(5, 7);
            month = getTimeUnitInteger(monthString, "Month");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Month string is less than 2 characters");
        }
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month cannot be less than 1 or more than 12");
        return month;
    }

    private int getYear() {
        int year;
        try {
            String yearString = dateAndTimeString.substring(0, 4);
            year = getTimeUnitInteger(yearString, "Year");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Year string is less than 4 characters");
        }
        if (year < 2000 || year > 2012)
            throw new IllegalArgumentException("Year cannot be less than 2000 or more than 2012");
        return year;
    }

    private int getTimeUnitInteger(String dateString , String timeUnit) {
        if (dateString.matches("^[0-9]*$")) return Integer.parseInt(dateString);
        throw new IllegalArgumentException(timeUnit + " is not an integer");
    }
}
