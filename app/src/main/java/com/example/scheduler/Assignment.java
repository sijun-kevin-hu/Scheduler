package com.example.scheduler;

public class Assignment <T> implements Modifiable<T>{
    private String courseName;
    private int date;
    private double time;

    public Assignment (String courseName, int date, int time){
        this.date = date;
        this.time = time;
        this.courseName = courseName;
    }

    @Override
    public void add(T data) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public void edit(T data, int index) {

    }
}
