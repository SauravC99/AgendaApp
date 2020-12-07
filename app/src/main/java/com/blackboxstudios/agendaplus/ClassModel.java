package com.blackboxstudios.agendaplus;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClassModel {

    private int id;
    private String startTime;
    private String endTime;
    private String className;

    public ClassModel() {

    }

    public ClassModel(int id, String start, String end, String name) {
        this.id = id;
        this.startTime = start;
        this.endTime = end;
        this.className = name;
    }

    @Override
    public String toString() {
        return "Start time: " + startTime + '\n' +
                "End time: " + endTime + '\n' +
                "Class name: " + className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
