package com.epam.kinorating.factory;

import com.epam.kinorating.database.dao.CommentDao;
import com.epam.kinorating.database.dao.FilmDao;
import com.epam.kinorating.database.dao.MarkDao;
import com.epam.kinorating.database.dao.UserDao;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.*;
import com.epam.kinorating.service.impl.*;

public class ServiceFactory {
    private final DaoFactory daoFactory;

    public ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserService createUserService() {
        UserDao userDao = daoFactory.createUserDao();
        return new UserServiceImpl(userDao);
    }

    public CommentService createCommentService() {
        CommentDao commentDao = daoFactory.createCommentDao();
        return new CommentServiceImpl(commentDao);
    }

    public MarkService createMarkService() {
        MarkDao markDao = daoFactory.createMarkDao();
        return new MarkServiceImpl(markDao);
    }

    public FilmService createFilmService() {
        FilmDao filmDao = daoFactory.createFilmDao();
        CommentService commentService = createCommentService();
        return new FilmServiceImpl(filmDao, commentService);
    }

}