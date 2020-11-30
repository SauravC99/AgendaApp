package com.blackboxstudios.agendaplus;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CalendarEventModel {

    private int id;
    private String date;
    private String description;
    private String time;

    public CalendarEventModel() {

    }

    public CalendarEventModel(int id, String date, String description, String time) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Date: " + date + '\n' +
                "Description: " + description + '\n' +
                "Time: " + time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
