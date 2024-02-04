package com.example.scheduler;

public class Catalog<T> { //implements Modifiable<T>
    private Course[] courses;
    private Assignment[] assignments;
    static final int MAX_CAPACITY = 100;
    private int sizeOfCourses;
    private int sizeOfAssignments;

    public Catalog() {
        courses = new Course[MAX_CAPACITY];
        assignments = new Assignment[MAX_CAPACITY];
        sizeOfCourses = 0;
        sizeOfAssignments = 0;
    }

    /**
     * This method adds a course to the catalog.
     * @param added The course
     */
    public void addCourse(Course added) {
        checkData(added);
        int i = 0;
        while (courses[i] != null) {i++;}
        courses[i] = added;
        sizeOfCourses++;
    }

    /**
     * This method deletes assignment from index
     * @param index an index
     */
    public void deleteCourse(int index) {
        checkIndex(index);
        courses[index] = null;
        sizeOfCourses--;
    }

    /**
     * Returns the Course object of a given index
     * @param index an int index
     * @return a Course object
     */
    public Course getCourse(int index) {
        checkIndex(index);
        Course temp = courses[index];
        return temp;
    }

    /**
     * This method adds an assignment to the catalog
     * @param added is the assignmnet being added.
     */
    public void addAssignment(Assignment added) {
        checkData(added);
        int i = 0;
        while (assignments[i] != null) {i++;}
        assignments[i] = added;
        sizeOfAssignments++;
    }

    /**
     * This method edits the data at an index
     * @param data an assignment object
     * @param index an index
     */
    public void editCourse(Course data, int index) {
        checkData(data);
        checkIndex(index);
        courses[index] = data;
    }

    /**
     * This method deletes assignments from the catalog.
     * @param index is the index at which the assignment is being deleted.
     */
    public void deleteAssignment(int index) {
        checkIndex(index);
        assignments[index] = null;
        sizeOfAssignments--;
    }

    /**
     * This method edits an existing assignment in the catalog.
     * @param data is the new data being inputted.
     * @param index is the index at which to edit the data.
     */
    public void editAssignment(Assignment data, int index) {
        checkData(data);
        checkIndex(index);
        assignments[index] = data;
    }

    /**
     * Checks if data is empty
     * @param data an object T
     */
    private void checkData(Course data) {
        if (data == null) {
            throw new NullPointerException("The inputted data is empty.");
        }
    }
    private void checkData(Assignment data) {
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
        }
    }
}
