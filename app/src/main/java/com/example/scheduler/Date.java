package com.example.scheduler;

public class Date implements Comparable<Date> {
    private int month;
    private int day;
    private int year;

    public Date (int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public int compareTo(Date o) {
        return checkDate(o);
    }

    private int checkDate(Date o) {
        if (this.year < o.year) {
            return 1;
        } else if (this.year > o.year) {
            return -1;
        } else if (this.year == o.year) {
            if (this.month < o.month) {
                return 1;
            } else if (this.month > o.month) {
                return -1;
            } else if (this.month == o.month) {
                if (this.day < o.day) {
                    return 1;
                } else if (this.day > o.day) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
