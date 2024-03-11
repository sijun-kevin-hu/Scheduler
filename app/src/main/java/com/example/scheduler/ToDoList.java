package com.example.scheduler;

import androidx.annotation.NonNull;

public class ToDoList  {
    private String date;
    private String type;
    private String discr;

    public ToDoList(String date, String type, String discr) {
        this.date = date;
        this.type = type;
        this.discr = discr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }

    public String getName(){
        return getType();
    }


    public String getDueDate() {
        return null;
    }

    public Date getFormattedDate(){
        String[] format = date.split("/");
        int month = Integer.parseInt(format[0]);
        int day = Integer.parseInt(format[1]);
        int year = Integer.parseInt(format[2]);
        Date due = new Date(month, day, year);
        return due;
    }
}
