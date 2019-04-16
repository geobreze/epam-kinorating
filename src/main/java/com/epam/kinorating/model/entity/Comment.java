package com.epam.kinorating.model.entity;

import java.io.Serializable;

public class Comment extends Entity implements Serializable {
    public static final String NAME = "comment";

    private final User author;
    private final int filmId;
    private final String text;

    public Comment(Integer id, User author, int filmId, String text) {
        super(id);
        this.author = author;
        this.filmId = filmId;
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public int getFilmId() {
        return filmId;
    }

    //TODO:equals...
}
