package com.epam.kinorating.model.entity;

import java.io.Serializable;

public class User extends Entity implements Serializable {
    public static final String NAME = "user";

    private final String login;
    private final String password;
    private final Role role;
    private final boolean ban;

    public User(Integer id, String login, String password, Role role, boolean ban) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.ban = ban;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isBan() {
        return ban;
    }

    //TODO:equals...
}
