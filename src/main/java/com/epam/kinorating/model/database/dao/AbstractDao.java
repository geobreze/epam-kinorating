package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.database.ConnectionPool;
import com.epam.kinorating.model.entity.Entity;
import com.epam.kinorating.model.entity.builder.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements DataAccessObject<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Optional<Connection> optionalConnection = Optional.empty();
        try {
            Connection connection = connectionPool.getConnection();
            optionalConnection = Optional.of(connection);

            PreparedStatement statement = connection.prepareStatement(query);
            prepareStatement(statement, params);

            LOGGER.debug("Prepared statement: {}", statement);

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            optionalConnection.ifPresent(connectionPool::releaseConnection);
        }
    }

    protected List<T> executeQuery(String query, Builder<T> builder, Object... params) throws DaoException {
        List<T> resultList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Optional<Connection> optionalConnection = Optional.empty();
        try {
            Connection connection = connectionPool.getConnection();
            optionalConnection = Optional.of(connection);

            PreparedStatement statement = connection.prepareStatement(query);
            prepareStatement(statement, params);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T builtObject = builder.build(resultSet);
                resultList.add(builtObject);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            optionalConnection.ifPresent(connectionPool::releaseConnection);
        }
        return resultList;
    }

    protected Optional<T> executeQueryForSingleResult(String query, Builder<T> builder, Object... params) throws DaoException {
        List<T> itemsList = executeQuery(query, builder, params);
        Optional<T> result;
        if(itemsList.size() == 1) {
            T firstItem = itemsList.get(0);
            result = Optional.of(firstItem);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    protected String buildRemoveByIdQuery(String tableName) {
        return "DELETE FROM " + tableName + " WHERE id = ?";
    }

    private void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 1; i < params.length + 1; i++) {
            statement.setObject(i, params[i - 1]);
        }
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
