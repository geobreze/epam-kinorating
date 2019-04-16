package com.epam.kinorating.model.entity.builder;

import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentBuilder implements Builder<Comment> {
    private final Builder<User> userBuilder;
    private static final String ID_LABEL = "comment_id";
    private static final String FILM_ID_LABEL = "film_id";
    private static final String TEXT_LABEL = "text";

    public CommentBuilder(Builder<User> userBuilder) {
        this.userBuilder = userBuilder;
    }

    @Override
    public Comment build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_LABEL);
        User author = userBuilder.build(resultSet);
        int filmId = resultSet.getInt(FILM_ID_LABEL);
        String text = resultSet.getString(TEXT_LABEL);
        return new Comment(id, author, filmId, text);
    }
}
