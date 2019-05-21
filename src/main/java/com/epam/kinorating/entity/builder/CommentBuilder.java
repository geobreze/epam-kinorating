package com.epam.kinorating.entity.builder;

import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentBuilder implements Builder<Comment> {
    private static final String ID_LABEL = "comment_id";
    private static final String FILM_ID_LABEL = "film_id";
    private static final String TEXT_LABEL = "text";
    private static final String UPDATE_TIME_LABEL = "last_update";
    private final Builder<User> userBuilder;

    public CommentBuilder(Builder<User> userBuilder) {
        this.userBuilder = userBuilder;
    }

    @Override
    public Comment build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_LABEL);
        User author = userBuilder.build(resultSet);
        int filmId = resultSet.getInt(FILM_ID_LABEL);
        String text = resultSet.getString(TEXT_LABEL);
        Timestamp updateTimestamp = resultSet.getTimestamp(UPDATE_TIME_LABEL);
        LocalDateTime updateTime = updateTimestamp.toLocalDateTime();
        return new Comment(id, author, filmId, text, updateTime);
    }
}
