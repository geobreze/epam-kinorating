package com.epam.kinorating.service;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.database.dao.FilmDao;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.Film;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FilmService implements Service<Film> {
    private final FilmDao filmDao;
    private final CommentService commentService;

    public FilmService(FilmDao filmDao, CommentService commentService) {
        this.filmDao = filmDao;
        this.commentService = commentService;
    }

    public List<Film> findAll() throws ServiceException {
        try {
            return filmDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Film> findById(Integer id) throws ServiceException {
        try {
            return filmDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Film> findByIdWithComments(Integer id) throws ServiceException {
        try {
            List<Comment> comments = commentService.findCommentsByFilmId(id);
            Optional<Film> filmOptional = filmDao.findById(id);
            filmOptional.ifPresent(film -> film.setComments(comments));
            if(!filmOptional.isPresent()) {
                System.out.println("Film is empty " + id);
            }
            return filmOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeById(Integer id) throws ServiceException {
        try {
            filmDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void saveFilm(Film film) throws ServiceException {
        try {
            filmDao.save(film);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
