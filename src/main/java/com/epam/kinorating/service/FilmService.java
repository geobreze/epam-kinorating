package com.epam.kinorating.service;

import com.epam.kinorating.entity.Film;
import com.epam.kinorating.exception.ServiceException;

import java.util.Optional;

public interface FilmService extends Service<Film>, Pageable<Film> {
    Optional<Film> findById(Integer id) throws ServiceException;

    Optional<Film> findByIdWithComments(Integer id) throws ServiceException;

    void removeById(Integer id) throws ServiceException;

    void save(Film film) throws ServiceException;

}
