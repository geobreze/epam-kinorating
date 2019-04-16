package com.epam.kinorating.service;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.database.dao.CommentDao;
import com.epam.kinorating.model.entity.Comment;

import java.io.IOException;
import java.util.List;

public class CommentService implements Service<Comment> {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> findCommentsByFilmId(Integer id) throws ServiceException {
        try {
            return commentDao.findCommentsByFilmId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addNewComment(Comment comment) throws ServiceException {
        try {
            commentDao.save(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeById(Integer id) throws ServiceException {
        try {
            commentDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void close() throws IOException {
        commentDao.close();
    }
}
