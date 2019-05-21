package com.epam.kinorating.database.dao;

import com.epam.kinorating.database.ProxyConnection;
import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.entity.builder.Builder;
import com.epam.kinorating.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class MarkDao extends AbstractDao<Mark> {
    private static final String FIND_ALL_QUERY = "SELECT id AS mark_id, user_id, film_id, value FROM mark";
    private static final String FIND_BY_ID_QUERY = "SELECT id AS mark_id, user_id, film_id, value FROM mark WHERE id = ?";
    private static final String FIND_BY_USER_AND_FILM_ID_QUERY = "SELECT id AS mark_id, user_id, film_id, value FROM mark WHERE user_id = ? AND film_id = ?";
    private static final String REMOVE_BY_USER_AND_FILM_ID_QUERY = "DELETE FROM mark WHERE user_id = ? AND film_id = ?";
    private static final String ADD_MARK_QUERY = "INSERT INTO mark (film_id, user_id, value) VALUES (?, ?, ?)";

    public MarkDao(ProxyConnection connection, Builder<Mark> builder) {
        super(connection, builder);
    }

    @Override
    public List<Mark> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public int getEntriesCount() throws DaoException {
        return getEntriesCount(Mark.NAME);
    }

    @Override
    public List<Mark> findAllWithLimitAndOffset(int limit, int offset) throws DaoException {
        String query = addLimitAndOffsetToQuery(FIND_ALL_QUERY);
        return executeQuery(query, limit, offset);
    }

    @Override
    public Optional<Mark> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, id);
    }

    @Override
    public void save(Mark entity) throws DaoException {
        // update makes no sense
        if (entity.getId() == null) {
            executeUpdate(ADD_MARK_QUERY, entity.getFilmId(), entity.getUserId(), entity.getValue());
        }
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(Mark.NAME);
        executeUpdate(query, id);
    }

    public void removeByUserAndFilmId(int userId, int filmId) throws DaoException {
        executeUpdate(REMOVE_BY_USER_AND_FILM_ID_QUERY, userId, filmId);
    }

    public Optional<Mark> findByUserAndFilmId(int userId, int filmId) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_USER_AND_FILM_ID_QUERY, userId, filmId);
    }
}
