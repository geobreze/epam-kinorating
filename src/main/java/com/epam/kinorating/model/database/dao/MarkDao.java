package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.builder.MarkBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MarkDao extends AbstractDao<Mark> {
    private static final String FIND_BY_USER_AND_FILM_ID_QUERY = "SELECT id AS mark_id, user_id, film_id, value FROM mark WHERE user_id = ? AND film_id = ?";
    private static final String REMOVE_BY_USER_AND_FILM_ID_QUERY = "DELETE FROM mark WHERE user_id = ? AND film_id = ?";
    private static final String ADD_MARK_QUERY = "INSERT INTO mark (film_id, user_id, value) VALUES (?, ?, ?)";

    public MarkDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Mark> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Mark> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(Mark entity) throws DaoException {
        executeUpdate(ADD_MARK_QUERY, entity.getFilmId(), entity.getUserId(), entity.getValue());
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(Mark.NAME);
        executeUpdate(query, id);
    }

    public void removeByUserAndFilmId(int userId, int filmId) throws DaoException {
        executeUpdate(REMOVE_BY_USER_AND_FILM_ID_QUERY, userId, filmId);
    }

    @Override
    public void update(Integer id, Mark entity) throws DaoException {

    }

    public Optional<Mark> findByUserAndFilmId(int userId, int filmId) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_USER_AND_FILM_ID_QUERY, new MarkBuilder(), userId, filmId);
    }
}
