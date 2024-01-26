package com.example.scheduler;

public class Course<T> implements Modifiable<T> {
    private T[] backingArray;
    private int size = 0;
    private String name;

    public Course(String name) {
        backingArray = (T[]) new Object[100];
        this.name = name;
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
