package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.entity.Role;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.model.entity.builder.UserBuilder;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {
    private static final String FIND_BY_ID_QUERY = "SELECT id AS user_id, login, password, role, ban FROM user WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id AS user_id, login, password, role, ban FROM user";
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id AS user_id, login, password, role, ban FROM user WHERE login = ? AND password = MD5(?);";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET login = ?, password = MD5(?), role = ?, ban = ? WHERE id = ?";
    private static final String UPDATE_BAN_AND_ROLE_QUERY = "UPDATE user SET role = ?, ban = ? WHERE id = ?";


    public UserDao(Connection connection) {
        super(connection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_LOGIN_AND_PASSWORD_QUERY, new UserBuilder(), login, password);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY, new UserBuilder());
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, new UserBuilder(), id);
    }

    @Override
    public void update(Integer id, User entity) throws DaoException {
        String banString = entity.isBan() ? "1" : "0";
        executeUpdate(UPDATE_USER_QUERY, entity.getLogin(), entity.getPassword(), entity.getRole().name(), banString, entity.getId());
    }

    public void updateRoleAndBanStatus(Integer id, Role role, boolean ban) throws DaoException {
        String banString = ban ? "1" : "0";
        executeUpdate(UPDATE_BAN_AND_ROLE_QUERY, role.name(), banString, id);
    }

    @Override
    public void save(User entity) throws DaoException {
        // empty...
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(User.NAME);
        executeUpdate(query, id);
    }
}
