package com.epam.kinorating.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProxyConnection implements AutoCloseable {
    private Connection connection;

    public ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public Statement createStatement(String sql) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
