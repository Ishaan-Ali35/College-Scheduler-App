package com.example.a2340project_1;

public class Assignment {
    private String title;
    private String dueDate;
    private String course;

    public Assignment(String title, String dueDate, String course) {
        this.title = title;
        this.dueDate = dueDate;
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Assignment: " + title + "\n" + "Course: " + course + "\n" + "Due: " + dueDate + "\n";
    }
}
