package com.epam.kinorating.service;

import com.epam.kinorating.entity.Status;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.ServiceException;

import java.util.Optional;

public interface UserService extends Service<User>, Pageable<User> {
    Optional<User> findById(Integer id) throws ServiceException;

    void updateStatus(Integer id, Status status) throws ServiceException;

    void updateBan(Integer id, boolean ban) throws ServiceException;

    Optional<User> login(String login, String password) throws ServiceException;
}
