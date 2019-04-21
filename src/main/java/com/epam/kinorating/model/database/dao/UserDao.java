package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.entity.Role;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.model.entity.builder.Builder;
import com.epam.kinorating.model.entity.builder.UserBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {
    private static final String FIND_BY_ID_QUERY = "SELECT id AS user_id, login, password, role, ban FROM user WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id AS user_id, login, password, role, ban FROM user";
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id AS user_id, login, password, role, ban FROM user WHERE login = ? AND password = MD5(?);";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET login = ?, password = MD5(?), role = ?, ban = ? WHERE id = ?";
    private static final String UPDATE_BAN_AND_ROLE_QUERY = "UPDATE user SET role = ?, ban = ? WHERE id = ?";


    public UserDao(Connection connection, Builder<User> builder) {
        super(connection, builder);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_LOGIN_AND_PASSWORD_QUERY, login, password);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, id);
    }

    public void updateRoleAndBanStatus(Integer id, Role role, boolean ban) throws DaoException {
        String banString = ban ? "1" : "0";
        executeUpdate(UPDATE_BAN_AND_ROLE_QUERY, role.name(), banString, id);
    }

    @Override
    public void save(User entity) throws DaoException {
        if(entity.getId() == null) {
            // empty...
        } else {
            String banString = entity.isBan() ? "1" : "0";
            executeUpdate(UPDATE_USER_QUERY, entity.getLogin(), entity.getPassword(), entity.getRole().name(), banString, entity.getId());
        }
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(User.NAME);
        executeUpdate(query, id);
    }
}
