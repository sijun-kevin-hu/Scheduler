package com.example.scheduler;

import java.util.ArrayList;

public class ClassEntry<T> implements Modifiable<T> {
    private ArrayList<T> list = new ArrayList<>();
    private String name;
    private String date;
    private int gradePercentage;

    public ClassEntry() {

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
