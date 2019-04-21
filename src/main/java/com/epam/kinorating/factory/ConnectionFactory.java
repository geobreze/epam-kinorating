package com.epam.kinorating.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static final String USER_PROPERTY = "db.user";
    private static final String PASSWORD_PROPERTY = "db.password";
    private static final String URL_PROPERTY = "db.url";
    private final String url;
    private final String user;
    private final String password;

    public ConnectionFactory(String config) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(config);
        url = resourceBundle.getString(URL_PROPERTY);
        user = resourceBundle.getString(USER_PROPERTY);
        password = resourceBundle.getString(PASSWORD_PROPERTY);
    }

    public Connection create() throws SQLException {
        LOGGER.info("Creating connection");
        return DriverManager.getConnection(url, user, password);
    }

}
