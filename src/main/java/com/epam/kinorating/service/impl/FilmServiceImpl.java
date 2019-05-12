package com.epam.kinorating.service.impl;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.database.dao.FilmDao;
import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.service.CommentService;
import com.epam.kinorating.service.FilmService;
import com.epam.kinorating.service.Pageable;

import java.util.List;
import java.util.Optional;

public class FilmServiceImpl implements FilmService {
    private static final int MAX_TITLE_LENGTH = 255;

    private final FilmDao filmDao;
    private final CommentService commentService;
    private final Pageable<Film> pageableLogic;

    public FilmServiceImpl(FilmDao filmDao, CommentService commentService, Pageable<Film> pageableLogic) {
        this.filmDao = filmDao;
        this.commentService = commentService;
        this.pageableLogic = pageableLogic;
    }

    @Override
    public int countPages(int elementsOnPage) throws ServiceException {
        return pageableLogic.countPages(elementsOnPage);
    }

    @Override
    public List<Film> findAllOnPage(int currentPage, int itemsOnPage) throws ServiceException {
        return pageableLogic.findAllOnPage(currentPage, itemsOnPage);
    }

    @Override
    public List<Film> findAll() throws ServiceException {
        try {
            return filmDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Film> findById(Integer id) throws ServiceException {
        try {
            return filmDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Film> findByIdWithComments(Integer id) throws ServiceException {
        try {
            List<Comment> comments = commentService.findAllByFilmId(id);
            Optional<Film> filmOptional = filmDao.findById(id);
            filmOptional.ifPresent(film -> film.setComments(comments));
            return filmOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeById(Integer id) throws ServiceException {
        try {
            filmDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Film film) throws ServiceException {
        String title = film.getTitle();
        String description = film.getDescription();
        if (title.isEmpty() || description.isEmpty()) {
            throw new ServiceException("Title and description can't be empty");
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new ServiceException("Title is too long");
        }
        try {
            filmDao.save(film);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
