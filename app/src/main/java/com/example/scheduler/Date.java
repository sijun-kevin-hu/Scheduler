package com.example.scheduler;

public class Date implements Comparable<Date> {
    private int month;
    private int day;
    private int year;

    public Date (int month, int day, int year) {
        checkDateInput(month, day, year);
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public int compareTo(Date o) {
        return checkDate(o);
    }

    private int checkDate(Date o) {
        if (this.year != o.year) {
            return Integer.compare(this.year, o.year);
            } else if (this.month != o.month) {
                return Integer.compare(this.month, o.month);
                } else if (this.day != o.day) {
                    return Integer.compare(this.day, o.day);
                }
        return 0;
    }

    private void checkDateInput(int month, int day, int year) {
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("The inputted date is not a real date.");
        } else if (day <= 0 || day > 31) {
            throw new IllegalArgumentException("The inputted date is not a real date. ");
        } else if (year <= 0) {
            throw new IllegalArgumentException("The inputted date is not a real date");
        }
    }
}
