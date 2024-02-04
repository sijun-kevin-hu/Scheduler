package com.example.scheduler;

/**
 * This class represents a Course object
 */
public class Course {
    private String name;
    private String instructor;

    /**
     * This constructor initializes assignments and sets name variable.
     * @param name a name for the Course
     */
    public Course(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
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
        this.name = name;
    }

    public void setInstructor(String instructor) { this.instructor = instructor};

    public void getInstructor() { return instructor};
}
