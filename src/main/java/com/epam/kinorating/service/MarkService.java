package com.epam.kinorating.service;

import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.exception.ServiceException;

import java.util.Optional;

public interface MarkService extends Service<Mark> {
    Optional<Mark> findMarkByUserAndFilmId(Integer userId, Integer filmId) throws ServiceException;

    void save(Mark mark) throws ServiceException;

    void removeByUserAndFilmId(int userId, int filmId) throws ServiceException;

}
