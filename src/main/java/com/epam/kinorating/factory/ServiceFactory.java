package com.epam.kinorating.factory;

import com.epam.kinorating.database.dao.CommentDao;
import com.epam.kinorating.database.dao.FilmDao;
import com.epam.kinorating.database.dao.MarkDao;
import com.epam.kinorating.database.dao.UserDao;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.*;
import com.epam.kinorating.service.impl.*;
import com.epam.kinorating.service.utils.PageableLogic;

public class ServiceFactory {
    private final DaoFactory daoFactory;

    public ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserService createUserService() {
        UserDao userDao = daoFactory.createUserDao();
        Pageable<User> pageable = new PageableLogic<>(userDao);
        return new UserServiceImpl(userDao, pageable);
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
        Pageable<Film> pageable = new PageableLogic<>(filmDao);
        return new FilmServiceImpl(filmDao, commentService, pageable);
    }

}