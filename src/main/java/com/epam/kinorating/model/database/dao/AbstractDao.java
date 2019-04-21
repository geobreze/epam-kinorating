package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.database.ConnectionPool;
import com.epam.kinorating.model.entity.Entity;
import com.epam.kinorating.model.entity.builder.Builder;
import com.sun.javafx.binding.StringFormatter;
import javafx.beans.binding.StringExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements DataAccessObject<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ?";

    private final Connection connection;
    private final Builder<T> builder;

    public AbstractDao(Connection connection, Builder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            prepareStatement(statement, params);

            LOGGER.debug("Prepared statement: {}", statement);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> resultList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            prepareStatement(statement, params);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if(!resultSet.wasNull()) {
                    T builtObject = builder.build(resultSet);
                    resultList.add(builtObject);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultList;
    }

    protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DaoException {
        List<T> itemsList = executeQuery(query, params);
        Optional<T> result = Optional.empty();
        if (itemsList.size() == 1) {
            T firstItem = itemsList.get(0);
            result = Optional.of(firstItem);
        }
        return result;
    }

    protected String buildRemoveByIdQuery(String tableName) {
        StringExpression queryExpression = StringFormatter.format(DELETE_BY_ID_QUERY, tableName);
        return queryExpression.getValue();
    }

    private void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 1; i < params.length + 1; i++) {
            statement.setObject(i, params[i - 1]);
        }
    }
}
