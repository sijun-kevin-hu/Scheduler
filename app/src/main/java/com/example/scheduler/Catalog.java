package com.example.scheduler;

public class Catalog<T> implements Modifiable<T> {
    private T[] courses;
    static final int MAX_CAPACITY = 100;
    private int size;

    public Catalog() {
        courses = (T[]) new Object[100];
        size = 0;
    }

    /**
     * This method adds an assignment to Course
     * @param data The assignment
     */
    @Override
    public void add(T data) {
        checkData(data);
        int i = 0;
        while (courses[i] != null) {i++;}
        courses[i] = data;
        size++;
    }

    /**
     * This method deletes assignment from index
     * @param index an index
     */
    @Override
    public void delete(int index) {
        checkIndex(index);
        courses[index] = null;
        size--;
    }

    /**
     * This method edits the data at an index
     * @param data an assignment object
     * @param index an index
     */
    @Override
    public void edit(T data, int index) {
        checkData(data);
        checkIndex(index);
        courses[index] = data;
    }

    /**
     * Checks if data is empty
     * @param data an object T
     */
    private void checkData(T data) {
        if (data == null) {
            throw new NullPointerException("The inputted data is empty.");
        }
    }

    /**
     * Checks if index is out of bounds or empty
     * @param index an int index
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= MAX_CAPACITY) {
            throw new IndexOutOfBoundsException("The inputted index is out of bounds.");
        } else if (courses[index] == null) {
            throw new NullPointerException("The array is empty at that index.");
        }
    }
}
