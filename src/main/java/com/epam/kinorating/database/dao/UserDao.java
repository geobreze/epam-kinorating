package com.epam.kinorating.database.dao;

import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.Status;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.database.ProxyConnection;
import com.epam.kinorating.database.utils.Hasher;
import com.epam.kinorating.entity.Role;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.builder.Builder;

import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {
    private static final String FIND_BY_ID_QUERY = "SELECT id AS user_id, login, password, role, ban, status FROM user WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id AS user_id, login, password, role, ban, status FROM user";
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id AS user_id, login, password, role, ban, status FROM user WHERE login = ? AND password = ?;";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET login = ?, password = ?, role = ?, ban = ?, status = ? WHERE id = ?";
    private static final String UPDATE_STATUS_QUERY = "UPDATE user SET status = ? WHERE id = ?";
    private static final String UPDATE_BAN_QUERY = "UPDATE user SET ban = ? WHERE id = ?";

    private final Hasher hasher;

    public UserDao(ProxyConnection connection, Builder<User> builder, Hasher hasher) {
        super(connection, builder);
        this.hasher = hasher;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        String hashedPassword = hasher.hash(password);
        return executeQueryForSingleResult(FIND_BY_LOGIN_AND_PASSWORD_QUERY, login, hashedPassword);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public List<User> findAllWithLimitAndOffset(int limit, int offset) throws DaoException {
        String query = addLimitAndOffsetToQuery(FIND_ALL_QUERY);
        return executeQuery(query, limit, offset);
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        return executeQueryForSingleResult(FIND_BY_ID_QUERY, id);
    }

    public void updateBan(Integer id, boolean ban) throws DaoException {
        String banString = ban ? "1" : "0";
        executeUpdate(UPDATE_BAN_QUERY, banString, id);
    }

    public void updateStatus(Integer id, Status status) throws DaoException {
        executeUpdate(UPDATE_STATUS_QUERY, status.name(), id);
    }

    @Override
    public int getEntriesCount() throws DaoException {
        return getEntriesCount(User.NAME);
    }

    @Override
    public void save(User entity) throws DaoException {
        // insert is not implemented since
        // scope of the project does not include registration
        if (entity.getId() != null) {
            String banString = entity.isBan() ? "1" : "0";
            String hashedPassword = hasher.hash(entity.getPassword());
            executeUpdate(UPDATE_USER_QUERY, entity.getLogin(), hashedPassword, entity.getRole().name(), banString, entity.getId());
        }
    }

    @Override
    public void removeById(Integer id) throws DaoException {
        String query = buildRemoveByIdQuery(User.NAME);
        executeUpdate(query, id);
    }
}