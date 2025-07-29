package com.ex.project;

import java.io.Serializable;

public class Workout implements Serializable {
    String type;
    String date;
    int duration;
    String notes;

    public Workout(String type, String date, int duration, String notes) {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.notes = notes;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public int getDuration() {
        return duration;
    }
    public String getNotes() {
        return notes;
    }
}
