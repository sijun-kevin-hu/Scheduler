package com.example.scheduler;

/**
 * This class represents a Course object
 */
public class Course<T> implements Modifiable<T> {
    private String name;

    /**
     * This constructor initializes assignments and sets name variable.
     * @param name a name for the Course
     */
    public Course(String name) {
        this.name = name;
    }

    @Override
    public void add(T data) {
        checkData(data);
        int i = 0;
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
    }

    @Override
    public void edit(T data, int index) {
        checkData(data);
        checkIndex(index);
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
}
