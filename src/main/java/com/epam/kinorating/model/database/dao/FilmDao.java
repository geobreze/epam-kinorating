package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.model.entity.builder.FilmBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class FilmDao extends AbstractDao<Film> {
    private static final int MINIMUM_MARK_COUNT_BORDER = 2;
    private static final String FIND_ALL_QUERY = "SELECT f.id AS film_id, f.title, f.description, IF(COUNT(m.value) >= ?, AVG(m.value), NULL) AS mark FROM film f LEFT JOIN mark m ON f.id = m.film_id GROUP BY f.id ORDER BY f.last_update DESC";
    private static final String FIND_BY_ID_QUERY = "SELECT f.id AS film_id, f.title, f.description, IF(COUNT(m.value) >= ?, AVG(m.value), NULL) as mark FROM film f LEFT JOIN mark m ON f.id = m.film_id WHERE f.id = ?";
    private static final String ADD_FILM_QUERY = "INSERT INTO film (title, description) VALUES (?, ?)";
    private static final String UPDATE_FILM_QUERY = "UPDATE film SET title = ?, description = ? WHERE id = ?";

    public FilmDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Film> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY, new FilmBuilder(), MINIMUM_MARK_COUNT_BORDER);
    }

    @Override
    public Optional<Film> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, new FilmBuilder(), MINIMUM_MARK_COUNT_BORDER, id);
    }

    @Override
    public void save(Film entity) throws DaoException {
        executeUpdate(ADD_FILM_QUERY, entity.getTitle(), entity.getDescription());
    }

    @Override
    public void update(Integer id, Film entity) throws DaoException {
        executeUpdate(UPDATE_FILM_QUERY, entity.getTitle(), entity.getDescription(), id);
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(Film.NAME);
        executeUpdate(query, id);
    }
}