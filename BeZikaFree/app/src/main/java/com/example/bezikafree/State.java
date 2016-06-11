package com.example.bezikafree;

/**
 * Created by samsiu on 6/11/16.
 */
public class State {
    int incidents;
    String name;
    String color;

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIncidents() {
        return incidents;
    }

    public void setIncidents(int incidents) {
        this.incidents = incidents;
    }

    String[] coordinates;

    public State(int incidents, String name, String[] coordinates) {
        this.incidents = incidents;
        this.name = name;
        this.color = determineColor();
        this.coordinates = coordinates;
    }

    public String determineColor(){
        if (incidents==0)return "#11990000";
        if (incidents==1||incidents==2) return "#77990000";
        if (incidents>2&&incidents<7) return "#AA990000";
        else return "#FF990000";
    }

}
