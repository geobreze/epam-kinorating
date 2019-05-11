package com.epam.kinorating.service;

import com.epam.kinorating.entity.Entity;
import com.epam.kinorating.exception.ServiceException;

import java.util.List;

public interface Pageable<T extends Entity> {
    List<T> findAllOnPage(int currentPage, int itemsOnPage) throws ServiceException;
    int countPages(int elementsOnPage) throws ServiceException;
}