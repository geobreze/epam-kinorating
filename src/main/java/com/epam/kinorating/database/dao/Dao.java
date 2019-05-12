package com.epam.kinorating.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    List<T> findAll() throws DaoException;

    List<T> findAllWithLimitAndOffset(int limit, int offset) throws DaoException;

    Optional<T> findById(Integer id) throws DaoException;

    void save(T entity) throws DaoException;

    void removeById(Integer id) throws DaoException;

    int getEntriesCount() throws DaoException;
}
