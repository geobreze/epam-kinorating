package com.epam.kinorating.service.impl;

import com.epam.kinorating.database.dao.UserDao;
import com.epam.kinorating.database.impl.UserDaoImpl;
import com.epam.kinorating.entity.Status;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends AbstractPageableService<User> implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
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
