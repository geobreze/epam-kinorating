package com.epam.kinorating.model.entity.builder;

import com.epam.kinorating.model.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmBuilder implements Builder<Film> {
    private static final String ID_LABEL = "film_id";
    private static final String TITLE_LABEL = "title";
    private static final String DESCRIPTION_LABEL = "description";
    private static final String MARK_LABEL = "mark";

    @Override
    public Film build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_LABEL);
        String title = resultSet.getString(TITLE_LABEL);
        String description = resultSet.getString(DESCRIPTION_LABEL);
        double mark = resultSet.getDouble(MARK_LABEL);
        return new Film(id, title, description, mark);
    }
}
