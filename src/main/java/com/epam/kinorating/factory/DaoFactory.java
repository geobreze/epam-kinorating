package com.epam.kinorating.factory;

import com.epam.kinorating.model.database.ProxyConnection;
import com.epam.kinorating.model.database.dao.CommentDao;
import com.epam.kinorating.model.database.dao.FilmDao;
import com.epam.kinorating.model.database.dao.MarkDao;
import com.epam.kinorating.model.database.dao.UserDao;
import com.epam.kinorating.model.database.utils.Hasher;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.model.entity.builder.Builder;

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
        return new UserDao(connection, userBuilder, hasher);
    }

    public CommentDao createCommentDao() {
        Builder<Comment> commentBuilder = builderFactory.createCommentBuilder();
        return new CommentDao(connection, commentBuilder);
    }

    public MarkDao createMarkDao() {
        Builder<Mark> markBuilder = builderFactory.createMarkBuilder();
        return new MarkDao(connection, markBuilder);
    }

    public FilmDao createFilmDao() {
        Builder<Film> filmBuilder = builderFactory.createFilmBuilder();
        return new FilmDao(connection, filmBuilder);
    }

}
