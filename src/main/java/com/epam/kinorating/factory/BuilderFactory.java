package com.epam.kinorating.factory;

import com.epam.kinorating.model.database.dao.CommentDao;
import com.epam.kinorating.model.database.dao.FilmDao;
import com.epam.kinorating.model.database.dao.MarkDao;
import com.epam.kinorating.model.database.dao.UserDao;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.model.entity.builder.*;

public class BuilderFactory {
    public Builder<User> createUserBuilder() {
        return new UserBuilder();
    }

    public Builder<Comment> createCommentBuilder() {
        Builder<User> userBuilder = createUserBuilder();
        return new CommentBuilder(userBuilder);
    }

    public Builder<Mark> createMarkBuilder() {
        return new MarkBuilder();
    }

    public Builder<Film> createFilmBuilder() {
        return new FilmBuilder();
    }

}
