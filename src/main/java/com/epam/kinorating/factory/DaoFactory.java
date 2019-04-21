package com.epam.kinorating.factory;

import com.epam.kinorating.model.database.dao.*;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.model.entity.builder.Builder;
import com.epam.kinorating.model.entity.builder.UserBuilder;

import java.sql.Connection;

public class DaoFactory {
    private final Connection connection;
    private final BuilderFactory builderFactory;

    public DaoFactory(Connection connection, BuilderFactory builderFactory) {
        this.connection = connection;
        this.builderFactory = builderFactory;
    }

    public UserDao createUserDao() {
        Builder<User> userBuilder = builderFactory.createUserBuilder();
        return new UserDao(connection, userBuilder);
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
