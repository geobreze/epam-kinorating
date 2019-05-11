package com.epam.kinorating.factory;

import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.builder.*;

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
