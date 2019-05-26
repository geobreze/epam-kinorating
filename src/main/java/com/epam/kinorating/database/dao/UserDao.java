package com.epam.kinorating.database.dao;

import com.epam.kinorating.entity.Status;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    void updateBan(Integer id, boolean ban) throws DaoException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void updateStatus(Integer id, Status status) throws DaoException;
}
