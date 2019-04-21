package com.epam.kinorating.factory;

import com.epam.kinorating.model.database.dao.CommentDao;
import com.epam.kinorating.model.database.dao.FilmDao;
import com.epam.kinorating.model.database.dao.MarkDao;
import com.epam.kinorating.model.database.dao.UserDao;
import com.epam.kinorating.service.*;

public class ServiceFactory {
    private final DaoFactory daoFactory;

    public ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserService createUserService() {
        UserDao userDao = daoFactory.createUserDao();
        return new UserService(userDao);
    }

    public CommentService createCommentService() {
        CommentDao commentDao = daoFactory.createCommentDao();
        return new CommentService(commentDao);
    }

    public MarkService createMarkService() {
        MarkDao markDao = daoFactory.createMarkDao();
        return new MarkService(markDao);
    }

    public FilmService createFilmService() {
        FilmDao filmDao = daoFactory.createFilmDao();
        CommentService commentService = createCommentService();
        return new FilmService(filmDao, commentService);
    }

}
