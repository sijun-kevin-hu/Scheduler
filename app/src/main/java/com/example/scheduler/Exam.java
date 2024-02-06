package com.example.scheduler;

import android.telephony.mbms.StreamingServiceInfo;

import com.example.scheduler.Date;

public class Exam implements NotificationItem{
    private String date;
    private String time;
    private String location;

    public Exam(String date, String time, String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String getDueDate(){
        return getDate();
    }
    @Override
    public String getName(){
      return  getLocation();
    }
    @Override
    public Date getFormattedDate(){
        String[] format = date.split("-");
        int month = Integer.parseInt(format[0]);
        int day = Integer.parseInt(format[1]);
        int year = Integer.parseInt(format[2]);
        Date due = new Date(month, day, year);
        return due;
    }
    @Override
    public String getType(){
        return"Exam at";
    }
}
