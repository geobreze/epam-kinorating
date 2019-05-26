package com.epam.kinorating.factory;

import com.epam.kinorating.database.ProxyConnection;
import com.epam.kinorating.database.dao.CommentDao;
import com.epam.kinorating.database.dao.FilmDao;
import com.epam.kinorating.database.dao.MarkDao;
import com.epam.kinorating.database.dao.UserDao;
import com.epam.kinorating.database.impl.CommentDaoImpl;
import com.epam.kinorating.database.impl.FilmDaoImpl;
import com.epam.kinorating.database.impl.MarkDaoImpl;
import com.epam.kinorating.database.impl.UserDaoImpl;
import com.epam.kinorating.database.utils.Hasher;
import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.builder.Builder;

public class DaoFactory {
    private final ProxyConnection connection;
    private final BuilderFactory builderFactory;
    private final Hasher hasher;

    public DaoFactory(ProxyConnection connection, BuilderFactory builderFactory, Hasher hasher) {
        this.connection = connection;
        this.builderFactory = builderFactory;
        this.hasher = hasher;
    }

    public UserDao createUserDao() {
        Builder<User> userBuilder = builderFactory.createUserBuilder();
        return new UserDaoImpl(connection, userBuilder, hasher);
    }

    public CommentDao createCommentDao() {
        Builder<Comment> commentBuilder = builderFactory.createCommentBuilder();
        return new CommentDaoImpl(connection, commentBuilder);
    }

    public MarkDao createMarkDao() {
        Builder<Mark> markBuilder = builderFactory.createMarkBuilder();
        return new MarkDaoImpl(connection, markBuilder);
    }

    public FilmDao createFilmDao() {
        Builder<Film> filmBuilder = builderFactory.createFilmBuilder();
        return new FilmDaoImpl(connection, filmBuilder);
    }

}
