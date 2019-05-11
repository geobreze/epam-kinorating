package com.epam.kinorating.service;

import com.epam.kinorating.entity.Entity;
import com.epam.kinorating.exception.ServiceException;

import java.util.List;

public interface Service<T extends Entity> {
    List<T> findAll() throws ServiceException;
}
