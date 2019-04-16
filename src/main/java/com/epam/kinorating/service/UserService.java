package com.epam.kinorating.service;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.database.dao.UserDao;
import com.epam.kinorating.model.entity.Role;
import com.epam.kinorating.model.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public User getById(Integer id) throws ServiceException {
        try {
            Optional<User> user = userDao.findById(id);
            return user.orElseThrow(ServiceException::new);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateRoleAndBanStatus(Integer id, Role role, boolean ban) throws ServiceException {
        try {
            userDao.updateRoleAndBanStatus(id, role, ban);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> tryLogin(String login, String password) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void close() throws IOException {
        userDao.close();
    }
}
