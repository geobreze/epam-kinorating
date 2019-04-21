package com.epam.kinorating.model.entity.builder;

import com.epam.kinorating.model.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmBuilder implements Builder<Film> {
    private static final int MINIMUM_MARK_COUNT_BORDER = 2;

    private static final String ID_LABEL = "film_id";
    private static final String TITLE_LABEL = "title";
    private static final String DESCRIPTION_LABEL = "description";
    private static final String MARK_LABEL = "mark";
    private static final String COUNT_LABEL = "count";

    @Override
    public Film build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_LABEL);
        String title = resultSet.getString(TITLE_LABEL);
        String description = resultSet.getString(DESCRIPTION_LABEL);
        int count = resultSet.getInt(COUNT_LABEL);
        double mark = count >= MINIMUM_MARK_COUNT_BORDER ? resultSet.getInt(MARK_LABEL) : 0;
        return new Film(id, title, description, mark);
    }
}
