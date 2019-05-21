package com.epam.kinorating.factory;

import com.epam.kinorating.database.ProxyConnection;
import com.epam.kinorating.exception.ConnectionFactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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
        Properties properties = new Properties();
        ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
        try(InputStream inputStream = classLoader.getResourceAsStream(config)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new ConnectionFactoryException("Properties file is corrupted", e);
        }
        url = properties.getProperty(URL_PROPERTY);
        user = properties.getProperty(USER_PROPERTY);
        password = properties.getProperty(PASSWORD_PROPERTY);
    }

    public ProxyConnection create() throws SQLException {
        LOGGER.debug("Creating connection");
        Connection connection = DriverManager.getConnection(url, user, password);
        return new ProxyConnection(connection);
    }

}
