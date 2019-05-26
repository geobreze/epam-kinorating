package com.epam.kinorating.database.dao;

import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.exception.DaoException;

import java.util.List;

public interface CommentDao extends Dao<Comment> {

    List<Comment> findCommentsByFilmId(int filmId) throws DaoException;

}
