package com.epam.kinorating.model.entity;

import java.io.Serializable;

public class Mark extends Entity implements Serializable {
    public static final String NAME = "mark";


    private final int filmId;
    private final int userId;
    private final int value;

    public Mark(Integer id, int value, int filmId, int userId) {
        super(id);
        this.value = value;
        this.userId = userId;
        this.filmId = filmId;
    }

    public int getValue() {
        return value;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getUserId() {
        return userId;
    }
}
