package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.model.entity.builder.CommentBuilder;
import com.epam.kinorating.model.entity.builder.UserBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CommentDao extends AbstractDao<Comment> {
    private static final String FIND_ALL_QUERY = "SELECT c.id AS comment_id, film_id, c.text, " +
            "c.user_id, u.login, u.password, u.role, u.ban FROM comment c " +
            "INNER JOIN user u ON u.id = c.user_id ORDER BY last_update DESC";
    private static final String FIND_BY_ID_QUERY = "SELECT c.id AS comment_id, film_id, c.text, " +
            "c.user_id, u.login, u.password, u.role, u.ban FROM comment c " +
            "INNER JOIN user u ON u.id = c.user_id WHERE c.id = ?";
    private static final String FIND_COMMENTS_BY_FILM_ID_QUERY = "SELECT c.id AS comment_id, film_id, c.text, " +
            "c.user_id, u.login, u.password, u.role, u.ban FROM comment c " +
            "INNER JOIN user u ON u.id = c.user_id WHERE c.film_id = ? ORDER BY last_update DESC";
    private static final String SAVE_COMMENT_QUERY = "INSERT INTO comment (user_id, film_id, text) VALUES (?, ?, ?)";

    public CommentDao(Connection connection) {
        super(connection);
    }

    public List<Comment> findCommentsByFilmId(int filmId) throws DaoException {
        return executeQuery(FIND_COMMENTS_BY_FILM_ID_QUERY, new CommentBuilder(new UserBuilder()), filmId);
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY, new CommentBuilder(new UserBuilder()));
    }

    @Override
    public Optional<Comment> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, new CommentBuilder(new UserBuilder()), id);
    }

    @Override
    public void update(Integer id, Comment entity) throws DaoException {
        // empty...
    }

    @Override
    public void save(Comment entity) throws DaoException {
        User author = entity.getAuthor();
        executeUpdate(SAVE_COMMENT_QUERY, author.getId(), entity.getFilmId(), entity.getText());
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(Comment.NAME);
        executeUpdate(query, id);
    }
}
