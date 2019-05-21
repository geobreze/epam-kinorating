package com.epam.kinorating.service.impl;

import com.epam.kinorating.database.dao.MarkDao;
import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MarkService;

import java.util.List;
import java.util.Optional;

public class MarkServiceImpl implements MarkService {
    private final MarkDao markDao;

    public MarkServiceImpl(MarkDao markDao) {
        this.markDao = markDao;
    }

    @Override
    public List<Mark> findAll() throws ServiceException {
        try {
            return markDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Mark> findMarkByUserAndFilmId(Integer userId, Integer filmId) throws ServiceException {
        try {
            return markDao.findByUserAndFilmId(userId, filmId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Mark mark) throws ServiceException {
        try {
            markDao.save(mark);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeByUserAndFilmId(int userId, int filmId) throws ServiceException {
        try {
            markDao.removeByUserAndFilmId(userId, filmId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
