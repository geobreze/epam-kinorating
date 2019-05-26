package com.epam.kinorating.database.impl;

import com.epam.kinorating.database.ProxyConnection;
import com.epam.kinorating.database.dao.CommentDao;
import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.builder.Builder;
import com.epam.kinorating.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {
    private static final String FIND_ALL_QUERY = "SELECT c.id AS comment_id, film_id, c.text, c.last_update, " +
            "c.user_id, u.login, u.password, u.role, u.ban, u.status FROM comment c " +
            "INNER JOIN user u ON u.id = c.user_id ORDER BY last_update DESC";
    private static final String FIND_BY_ID_QUERY = "SELECT c.id AS comment_id, film_id, c.text, c.last_update, " +
            "c.user_id, u.login, u.password, u.role, u.ban, u.status FROM comment c " +
            "INNER JOIN user u ON u.id = c.user_id WHERE c.id = ?";
    private static final String FIND_COMMENTS_BY_FILM_ID_QUERY = "SELECT c.id AS comment_id, film_id, c.text, c.last_update, " +
            "c.user_id, u.login, u.password, u.role, u.ban, u.status FROM comment c " +
            "INNER JOIN user u ON u.id = c.user_id WHERE c.film_id = ? ORDER BY last_update DESC";
    private static final String SAVE_COMMENT_QUERY = "INSERT INTO comment (user_id, film_id, text) VALUES (?, ?, ?)";

    public CommentDaoImpl(ProxyConnection connection, Builder<Comment> builder) {
        super(connection, builder);
    }

    @Override
    public List<Comment> findCommentsByFilmId(int filmId) throws DaoException {
        return executeQuery(FIND_COMMENTS_BY_FILM_ID_QUERY, filmId);
    }

    @Override
    public List<Comment> findAllWithLimitAndOffset(int limit, int offset) throws DaoException {
        String query = addLimitAndOffsetToQuery(FIND_ALL_QUERY);
        return executeQuery(query, limit, offset);
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public Optional<Comment> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, id);
    }

    @Override
    public int getEntriesCount() throws DaoException {
        return getEntriesCount(Comment.NAME);
    }

    @Override
    public void save(Comment entity) throws DaoException {
        // updating comments is redundant function
        if (entity.getId() == null) {
            User author = entity.getAuthor();
            executeUpdate(SAVE_COMMENT_QUERY, author.getId(), entity.getFilmId(), entity.getText());
        }
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(Comment.NAME);
        executeUpdate(query, id);
    }
}
