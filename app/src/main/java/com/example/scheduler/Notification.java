package com.example.scheduler;

public class Notification {
    private String Type;
    private String content;
    private String dueDate;

    public Notification(String type, String content, String dueDate) {
        Type = type;
        this.content = content;
        this.dueDate = dueDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
