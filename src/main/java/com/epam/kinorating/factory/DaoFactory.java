package com.epam.kinorating.factory;

import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.model.database.ConnectionPool;
import com.epam.kinorating.model.database.dao.*;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;

import java.sql.Connection;

public class DaoFactory {

    public static DataAccessObject create(String daoName) throws ConnectionPoolException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        DataAccessObject dataAccessObject;
        switch (daoName) {
            case User.NAME: {
                dataAccessObject = new UserDao(connection);
                break;
            }
            case Comment.NAME: {
                dataAccessObject = new CommentDao(connection);
                break;
            }
            case Mark.NAME: {
                dataAccessObject = new MarkDao(connection);
                break;
            }
            case Film.NAME: {
                dataAccessObject = new FilmDao(connection);
                break;
            }
            default: {
                ConnectionPool.getInstance().releaseConnection(connection);
                throw new IllegalArgumentException("Invalid dao name");
            }
        }
        return dataAccessObject;
    }

}
