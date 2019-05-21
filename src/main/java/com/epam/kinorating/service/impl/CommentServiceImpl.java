package com.epam.kinorating.service.impl;

import com.epam.kinorating.database.dao.CommentDao;
import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> findAll() throws ServiceException {
        try {
            return commentDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> findAllByFilmId(Integer id) throws ServiceException {
        try {
            return commentDao.findCommentsByFilmId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Comment comment) throws ServiceException {
        String text = comment.getText();
        if (text.isEmpty()) {
            throw new ServiceException("Comment body can't be empty");
        }
        try {
            commentDao.save(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeById(Integer id) throws ServiceException {
        try {
            commentDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
