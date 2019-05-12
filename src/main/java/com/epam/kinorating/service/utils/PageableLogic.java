package com.epam.kinorating.service.utils;

import com.epam.kinorating.database.dao.Dao;
import com.epam.kinorating.entity.Entity;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.Pageable;

import java.util.List;

public class PageableLogic<T extends Entity> implements Pageable<T> {
    private final Dao<T> dao;

    public PageableLogic(Dao<T> dao) {
        this.dao = dao;
    }

    @Override
    public int countPages(int elementsOnPage) throws ServiceException {
        try {
            int entriesCount = dao.getEntriesCount();
            double pages = (double) entriesCount / (double) elementsOnPage;
            return (int) Math.ceil(pages);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<T> findAllOnPage(int currentPage, int itemsOnPage) throws ServiceException {
        int offset = (currentPage - 1) * itemsOnPage;
        try {
            return dao.findAllWithLimitAndOffset(itemsOnPage, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
