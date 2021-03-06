package com.epam.kinorating.database.impl;

import com.epam.kinorating.database.ProxyConnection;
import com.epam.kinorating.database.dao.FilmDao;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.builder.Builder;
import com.epam.kinorating.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class FilmDaoImpl extends AbstractDao<Film> implements FilmDao {
    private static final String FIND_ALL_QUERY = "SELECT f.id AS film_id, f.title, f.description, AVG(m.value) AS mark, COUNT(m.value) AS count FROM film f LEFT JOIN mark m ON f.id = m.film_id GROUP BY f.id ORDER BY f.last_update DESC";
    private static final String FIND_BY_ID_QUERY = "SELECT f.id AS film_id, f.title, f.description, AVG(m.value) AS mark, COUNT(m.value) AS count FROM film f LEFT JOIN mark m ON f.id = m.film_id WHERE f.id = ? GROUP BY f.id";
    private static final String ADD_FILM_QUERY = "INSERT INTO film (title, description) VALUES (?, ?)";
    private static final String UPDATE_FILM_QUERY = "UPDATE film SET title = ?, description = ? WHERE id = ?";

    public FilmDaoImpl(ProxyConnection connection, Builder<Film> builder) {
        super(connection, builder);
    }

    @Override
    public List<Film> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public List<Film> findAllWithLimitAndOffset(int limit, int offset) throws DaoException {
        String query = addLimitAndOffsetToQuery(FIND_ALL_QUERY);
        return executeQuery(query, limit, offset);
    }

    @Override
    public int getEntriesCount() throws DaoException {
        return getEntriesCount(Film.NAME);
    }

    @Override
    public Optional<Film> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, id);
    }

    @Override
    public void save(Film entity) throws DaoException {
        if (entity.getId() == null) {
            executeUpdate(ADD_FILM_QUERY, entity.getTitle(), entity.getDescription());
        } else {
            executeUpdate(UPDATE_FILM_QUERY, entity.getTitle(), entity.getDescription(), entity.getId());
        }
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(Film.NAME);
        executeUpdate(query, id);
    }
}