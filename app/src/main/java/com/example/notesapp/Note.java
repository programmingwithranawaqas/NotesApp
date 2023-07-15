package com.example.notesapp;

public class Note {
    private String titleKey, descKey, dateKey;

    public Note() {
    }

    public Note(String titleKey, String descKey, String dateKey) {
        this.titleKey = titleKey;
        this.descKey = descKey;
        this.dateKey = dateKey;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public String getDescKey() {
        return descKey;
    }

    public void setDescKey(String descKey) {
        this.descKey = descKey;
    }

    public String getDateKey() {
        return dateKey;
    }

    public void setDateKey(String dateKey) {
        this.dateKey = dateKey;
    }

    @Override
    public String toString() {
        return "Note{" +
                "titleKey='" + titleKey + '\'' +
                ", descKey='" + descKey + '\'' +
                ", dateKey='" + dateKey + '\'' +
                '}';
    }
}
