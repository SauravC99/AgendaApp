package com.blackboxstudios.agendaplus;

public class ZoomClassModel {

    private String className;
    private String link;
    //private int buttonNum;

    // Constructors
    public ZoomClassModel(String className, String link) {
        this.className = className;
        this.link = link;
    }

    public ZoomClassModel() {
    }

    // Getters and setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    //toString just in case we want to print this
    @Override
    public String toString() {
        return "ZoomClassModel{" +
                "className='" + className + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
