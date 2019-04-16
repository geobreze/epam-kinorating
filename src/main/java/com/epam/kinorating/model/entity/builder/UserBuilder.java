package com.epam.kinorating.model.entity.builder;

import com.epam.kinorating.model.entity.Role;
import com.epam.kinorating.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    private static final String ID_LABEL = "user_id";
    private static final String LOGIN_LABEL = "login";
    private static final String PASSWORD_LABEL = "password";
    private static final String ROLE_LABEL = "role";
    private static final String BAN_LABEL = "ban";

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_LABEL);
        String login = resultSet.getString(LOGIN_LABEL);
        String password = resultSet.getString(PASSWORD_LABEL);
        String roleString = resultSet.getString(ROLE_LABEL);
        Role role = Role.valueOf(roleString);
        boolean ban = resultSet.getBoolean(BAN_LABEL);
        return new User(id, login, password, role, ban);
    }
}
