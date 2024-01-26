package com.example.scheduler;

public class Course<T> implements Modifiable<T> {
    private T[] backingArray;
    static final int MAX_CAPACITY = 100;
    private int size;
    private String name;

    public Course(String name) {
        backingArray = (T[]) new Object[MAX_CAPACITY];
        size = 0;
        this.name = name;
    }

    @Override
    public void add(T data) {
        checkData(data);
        int i = 0;
        while (backingArray[i] != null) {i++;}
        backingArray[i] = data;
        size++;
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        backingArray[index] = null;
        size--;
    }

    @Override
    public void edit(T data, int index) {
        checkData(data);
        checkIndex(index);
        backingArray[index] = data;
    }

    private void checkData(T data) {
        if (data == null) {
            throw new NullPointerException("The inputted data is empty.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= MAX_CAPACITY) {
            throw new IndexOutOfBoundsException("The inputted index is out of bounds.");
        } else if (backingArray[index] == null) {
            throw new NullPointerException("The array is empty at that index.");
        }
    }
}
