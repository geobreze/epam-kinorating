package com.epam.kinorating.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comment extends Entity implements Serializable {
    public static final String NAME = "comment";
    private static final long serialVersionUID = 766541853567987501L;

    private final User author;
    private final int filmId;
    private final String text;
    private final LocalDateTime updateTime;

    public Comment(Integer id, User author, int filmId, String text, LocalDateTime updateTime) {
        super(id);
        this.author = author;
        this.filmId = filmId;
        this.text = text;
        this.updateTime = updateTime;
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return filmId == comment.filmId &&
                Objects.equals(author, comment.author) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, filmId, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author +
                ", filmId=" + filmId +
                ", text='" + text + '\'' +
                ", updateTime=" + updateTime +
                "} " + super.toString();
    }
}