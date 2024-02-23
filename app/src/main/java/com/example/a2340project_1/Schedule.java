package com.example.a2340project_1;

public class Schedule {
    private String course;
    private String startTime;
    private String endTime;
    private String instructor;

    public Schedule(String course, String startTime, String endTime, String instructor) {
        this.course = course;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course: " + course + "\n" + "Start Time: " + startTime + "\n" + "End Time: " + endTime + "\nInstructor: " + instructor;
    }
}
