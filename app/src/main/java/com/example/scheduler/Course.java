package com.example.scheduler;

/**
 * This class represents a Course object
 */
public class Course<T> implements Modifiable<T> {
    private T[] assignments;
    private int size;
    private String name;

    /**
     * This constructor initializes assignments and sets name variable.
     * @param name a name for the Course
     */
    public Course(String name) {
        assignments = (T[]) new Object[Catalog.MAX_CAPACITY];
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
        if (index < 0 || index >= Catalog.MAX_CAPACITY) {
            throw new IndexOutOfBoundsException("The inputted index is out of bounds.");
        } else if (assignments[index] == null) {
            throw new NullPointerException("The array is empty at that index.");
        }
    }

    /**
     * This method checks if the inputted name is valid.
     * @param name is the name being checked.
     */
    private void checkName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The inputted name cannot be null");
        }
    }

    /**
     * A getter method for name variable
     * @return a String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * A setter method for name variable
     * @param name a String for name
     */
    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    public static void main(String[] args) {
        Object test1 = new Object();
        Object test2 = new Object();
        Object test3 = new Object();

        Course calculus = new Course("calculus");
        calculus.add(test1);
        calculus.add(test2);
        calculus.add(test3);
    }

}
