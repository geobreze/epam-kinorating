package com.epam.kinorating.service.impl;

import com.epam.kinorating.entity.Status;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.database.dao.UserDao;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.Pageable;
import com.epam.kinorating.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Pageable<User> pageableLogic;

    public UserServiceImpl(UserDao userDao, Pageable<User> pageableLogic) {
        this.userDao = userDao;
        this.pageableLogic = pageableLogic;
    }

    @Override
    public List<User> findAllOnPage(int currentPage, int itemsOnPage) throws ServiceException {
        return pageableLogic.findAllOnPage(currentPage, itemsOnPage);
    }

    @Override
    public int countPages(int elementsOnPage) throws ServiceException {
        return pageableLogic.countPages(elementsOnPage);
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateStatus(Integer id, Status status) throws ServiceException {
        try {
            userDao.updateStatus(id, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBan(Integer id, boolean ban) throws ServiceException {
        try {
            userDao.updateBan(id, ban);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
