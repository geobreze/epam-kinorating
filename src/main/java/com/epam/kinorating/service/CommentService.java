package com.epam.kinorating.service;

import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.exception.ServiceException;

import java.util.List;

public interface CommentService extends Service<Comment> {
    List<Comment> findAllByFilmId(Integer id) throws ServiceException;

    void save(Comment comment) throws ServiceException;

    void removeById(Integer id) throws ServiceException;
}
