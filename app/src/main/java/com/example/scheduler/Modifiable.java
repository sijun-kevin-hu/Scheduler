package com.example.scheduler;

public interface Modifiable<T> {
    public void add(T data);

    public void delete(int index);

    public void edit(T data, int index);
}
