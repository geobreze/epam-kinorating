package com.epam.kinorating.entity;

import java.util.Objects;

public class Mark extends Entity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mark mark = (Mark) o;
        return filmId == mark.filmId &&
                userId == mark.userId &&
                value == mark.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filmId, userId, value);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "filmId=" + filmId +
                ", userId=" + userId +
                ", value=" + value +
                "} " + super.toString();
    }
}