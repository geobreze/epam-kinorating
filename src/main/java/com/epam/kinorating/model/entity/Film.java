package com.epam.kinorating.model.entity;

import java.io.Serializable;
import java.util.List;

public class Film extends Entity implements Serializable {
    public static final String NAME = "film";

    private final String title;
    private final String description;
    private final double mark;
    private List<Comment> comments;

    public Film(Integer id, String title, String description, double mark) {
        super(id);
        this.title = title;
        this.description = description;
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getMark() {
        return mark;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mark=" + mark +
                ", comments=" + comments +
                '}';
    }

    //TODO:equals...
}
