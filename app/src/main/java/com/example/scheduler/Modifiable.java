package com.example.scheduler;

public interface Modifiable<T> {
    /**
     * This method adds an assignment to Course
     * @param data The assignment
     */
    public void add(T data);

    /**
     * This method deletes assignment from index
     * @param index an index
     */
    public void delete(int index);

    /**
     * This method edits the data at an index
     * @param data an assignment object
     * @param index an index
     */
    public void edit(T data, int index);
}
