package com.example.scheduler;

public class Assignment {
    private String courseName;
    private String assignmentName;
    private String dueDate;
    private String timeNotify;

    public Assignment (String courseName, String assignmentName, String dueDate) {
        this.courseName = courseName;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
       // this.timeNotify = timeNotify;
    }

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

    /**
     * Sets the date variable
     * @param date an integer date
     */
    public void setDueDate(String date) {
        this.dueDate = date;
    }

    /**
     * getDate: return the date of the assignments
     * @return date
     */
    public String getDueDate(){
        return this.dueDate;
    }

    /**
     * setTime: sets the time of the assignment
     * @param timeNotify a String
     */
    public void setTimeNotify(String timeNotify) {
        this.timeNotify = timeNotify;
    }

    /**
     * Sets the name variable
     * @param name a String name
     */
    public void setName(String name) {this.assignmentName = name;}

    /**
     * Returns the name variable
     * @return a String name
     */

    public String getName() {return this.assignmentName;}

    public Date getFormattedDate(){
        String[] format = dueDate.split("/");
        int month = Integer.parseInt(format[0]);
        int day = Integer.parseInt(format[1]);
        int year = Integer.parseInt(format[2]);
        Date due = new Date(month, day, year);
        return due;
    }


}
