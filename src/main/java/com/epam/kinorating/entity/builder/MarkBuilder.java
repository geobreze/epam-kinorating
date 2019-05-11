package com.epam.kinorating.entity.builder;

import com.epam.kinorating.entity.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarkBuilder implements Builder<Mark> {
    private static final String ID_LABEL = "mark_id";
    private static final String VALUE_LABEL = "value";
    private static final String USER_ID_LABEL = "user_id";
    private static final String FILM_ID_LABEL = "film_id";

    @Override
    public Mark build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_LABEL);
        int value = resultSet.getInt(VALUE_LABEL);
        int userId = resultSet.getInt(USER_ID_LABEL);
        int filmId = resultSet.getInt(FILM_ID_LABEL);
        return new Mark(id, value, filmId, userId);
    }
}
