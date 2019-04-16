package com.epam.kinorating.service;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.database.dao.MarkDao;
import com.epam.kinorating.model.entity.Mark;

import java.io.IOException;
import java.util.Optional;

public class MarkService implements Service<Mark> {
    private final MarkDao markDao;

    public MarkService(MarkDao markDao) {
        this.markDao = markDao;
    }

    public Optional<Mark> findMarkByUserAndFilmId(Integer userId, Integer filmId) throws ServiceException {
        try {
            return markDao.findByUserAndFilmId(userId, filmId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addMark(Mark mark) throws ServiceException {
        try {
            markDao.save(mark);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeMark(int userId, int filmId) throws ServiceException {
        try {
            markDao.removeByUserAndFilmId(userId, filmId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void close() throws IOException {
        markDao.close();
    }
}
