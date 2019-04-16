package com.epam.kinorating.factory;

import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.model.database.dao.CommentDao;
import com.epam.kinorating.model.database.dao.FilmDao;
import com.epam.kinorating.model.database.dao.MarkDao;
import com.epam.kinorating.model.database.dao.UserDao;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.service.*;

public class ServiceFactory {

    public static Service create(String serviceName) throws ConnectionPoolException {
        Service service;
        switch (serviceName) {
            case User.NAME: {
                UserDao userDao = (UserDao) DaoFactory.create(User.NAME);
                service = new UserService(userDao);
                break;
            }
            case Comment.NAME: {
                CommentDao commentDao = (CommentDao) DaoFactory.create(Comment.NAME);
                service = new CommentService(commentDao);
                break;
            }
            case Mark.NAME: {
                MarkDao markDao = (MarkDao) DaoFactory.create(Mark.NAME);
                service = new MarkService(markDao);
                break;
            }
            case Film.NAME: {
                FilmDao filmDao = (FilmDao) DaoFactory.create(Film.NAME);
                CommentService commentService = (CommentService) ServiceFactory.create(Comment.NAME);
                service = new FilmService(filmDao, commentService);
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid service name");
            }
        }
        return service;
    }

}
