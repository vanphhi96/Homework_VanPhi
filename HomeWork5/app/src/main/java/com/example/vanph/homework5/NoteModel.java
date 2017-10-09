package com.example.vanph.homework5;

/**
 * Created by vanph on 09/10/2017.
 */

public class NoteModel {
    private String title;
    private String description;
    private int id;
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public NoteModel(String title, String description) {

        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
