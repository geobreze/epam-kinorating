package com.epam.kinorating.factory;

import com.epam.kinorating.database.dao.CommentDao;
import com.epam.kinorating.database.dao.FilmDao;
import com.epam.kinorating.database.dao.MarkDao;
import com.epam.kinorating.database.dao.UserDao;
import com.epam.kinorating.service.UserService;
import com.epam.kinorating.service.CommentService;
import com.epam.kinorating.service.FilmService;
import com.epam.kinorating.service.MarkService;
import com.epam.kinorating.service.impl.CommentServiceImpl;
import com.epam.kinorating.service.impl.FilmServiceImpl;
import com.epam.kinorating.service.impl.MarkServiceImpl;
import com.epam.kinorating.service.impl.UserServiceImpl;

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