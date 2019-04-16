package com.epam.kinorating.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static final String DATABASE_URI = "jdbc:mysql://localhost/kinorating?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection create() throws SQLException {
        LOGGER.info("Creating connection");
        return DriverManager.getConnection(DATABASE_URI, USER, PASSWORD);
    }

}
