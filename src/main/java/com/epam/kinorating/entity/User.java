package com.epam.kinorating.entity;

import java.io.Serializable;
import java.util.Objects;

public class User extends Entity implements Serializable {
    public static final String NAME = "user";
    private static final long serialVersionUID = 4016739281518191102L;

    private final String login;
    private final transient String password;
    private final Role role;
    private final boolean ban;
    private final Status status;

    public User(Integer id, String login, String password, Role role, boolean ban, Status status) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.ban = ban;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return ban == user.ban &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, role, ban, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", ban=" + ban +
                ", status=" + status +
                "} " + super.toString();
    }
}
