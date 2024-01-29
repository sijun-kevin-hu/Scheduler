package com.example.scheduler;

public class Assignment {
    private String courseName;
    private int date;
    private double time;

    /**
     * setCourseName method: sets the name of tcourse
     * @param courseName
     */
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    /**
     * getCourseNAme: return the name of the course
     * @return courseName
     */
    public String getCourseName(){
        return this.courseName;
    }
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * getDate: return the date of the assignments
     * @return date
     */
    public int getDate(){
        return this.date;
    }

    /**
     * setTime: sets the time of the assignment
     * @param time
     */
    public void setTime(double time){
        this.time = time;
    }
}
