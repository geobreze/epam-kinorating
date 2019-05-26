package com.epam.kinorating.database.dao;

import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.exception.DaoException;

import java.util.Optional;

public interface MarkDao extends Dao<Mark> {

    void removeByUserAndFilmId(int userId, int filmId) throws DaoException;

    Optional<Mark> findByUserAndFilmId(int userId, int filmId) throws DaoException;

}
