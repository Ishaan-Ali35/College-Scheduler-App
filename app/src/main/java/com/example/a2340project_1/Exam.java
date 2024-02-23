package com.example.a2340project_1;

public class Exam {
    private String course;
    private String time;
    private String location;
    private String date;

    public Exam(String course, String date, String time, String location) {
        this.course = course;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "Course: " + course + "\n" + "Date: " + date + "\n" + "Time: " + time + "\nLocation: " + location;
    }
}
