package com.example.scheduler;

/**
 * This class represents a Course object
 */
public class Course<T> implements Modifiable<T> {
    private T[] assignments;
    static final int MAX_CAPACITY = 100;
    private int size;
    private String name;

    public Course(String name) {
        assignments = (T[]) new Object[MAX_CAPACITY];
        size = 0;
        this.name = name;
    }

    @Override
    public void add(T data) {
        checkData(data);
        int i = 0;
        while (assignments[i] != null) {i++;}
        assignments[i] = data;
        size++;
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        assignments[index] = null;
        size--;
    }

    @Override
    public void edit(T data, int index) {
        checkData(data);
        checkIndex(index);
        assignments[index] = data;
    }

    private void checkData(T data) {
        if (data == null) {
            throw new NullPointerException("The inputted data is empty.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= MAX_CAPACITY) {
            throw new IndexOutOfBoundsException("The inputted index is out of bounds.");
        } else if (assignments[index] == null) {
            throw new NullPointerException("The array is empty at that index.");
        }
    }
}
