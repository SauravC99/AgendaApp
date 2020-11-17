package com.blackboxstudios.agendaplus;

public class ZoomClassModel {

    private String className;
    private String link;
    private int buttonNum;

    // Constructors
    public ZoomClassModel(int buttonNum, String className, String link) {
        this.buttonNum = buttonNum;
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

    public int getButtonNum() {
        return buttonNum;
    }

    public void setButtonNum(int buttonNum) {
        this.buttonNum = buttonNum;
    }

    //toString just in case we want to print this
    @Override
    public String toString() {
        return "ZoomClassModel{" +
                "buttonNum='" + buttonNum + '\'' +
                ", className='" + className + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
