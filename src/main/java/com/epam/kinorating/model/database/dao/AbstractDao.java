package com.epam.kinorating.model.database.dao;

import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.model.database.ProxyConnection;
import com.epam.kinorating.model.entity.Entity;
import com.epam.kinorating.model.entity.builder.Builder;
import com.sun.javafx.binding.StringFormatter;
import javafx.beans.binding.StringExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ?";
    private static final String GET_COUNT_QUERY = "SELECT COUNT(*) row_count FROM ";
    private static final String ROW_COUNT = "row_count";

    private final ProxyConnection connection;
    private final Builder<T> builder;

    public AbstractDao(ProxyConnection connection, Builder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    protected int executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, params);

            LOGGER.debug("Prepared statement: {}", statement);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, params);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (!resultSet.wasNull()) {
                        T builtObject = builder.build(resultSet);
                        resultList.add(builtObject);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultList;
    }

    protected int getEntriesCount(String table) throws DaoException {
        String builtStatement = GET_COUNT_QUERY + table;
        try(Statement statement = connection.createStatement(builtStatement)) {
            try(ResultSet resultSet = statement.executeQuery(builtStatement)) {
                resultSet.next();
                return resultSet.getInt(ROW_COUNT);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
