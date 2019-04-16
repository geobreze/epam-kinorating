package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.entity.Entity;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T extends Entity> extends Closeable, AutoCloseable {
    List<T> findAll() throws DaoException;
    Optional<T> findById(Integer id) throws DaoException;
    void save(T entity) throws DaoException;
    void removeById(Integer id) throws DaoException;
    void update(Integer id, T entity) throws DaoException;
}
