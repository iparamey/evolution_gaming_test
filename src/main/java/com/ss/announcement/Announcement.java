package com.ss.announcement;


public class Announcement {

    private String description;

    public Announcement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
