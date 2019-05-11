package com.epam.kinorating.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Film extends Entity implements Serializable {
    public static final String NAME = "film";
    private static final long serialVersionUID = -8172685610954490728L;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return Double.compare(film.mark, mark) == 0 &&
                Objects.equals(title, film.title) &&
                Objects.equals(description, film.description) &&
                Objects.equals(comments, film.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, mark, comments);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mark=" + mark +
                ", comments=" + comments +
                "} " + super.toString();
    }
}
