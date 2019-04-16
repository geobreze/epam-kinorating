package com.epam.kinorating.model.database;

import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.factory.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int POOL_SIZE = 5;
    private static final ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        LOGGER.info("Creating connection pool");
        connections = new ArrayBlockingQueue<>(POOL_SIZE);
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initializePool() throws ConnectionPoolException {
        LOGGER.info("Initializing connection pool");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("JDBC driver is not found", e);
        }
        try {
            for (int i = 0; i < POOL_SIZE; ++i) {
                Connection connection = ConnectionFactory.create();
                connections.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Couldn't create connection", e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Couldn't get connection from pool", e);
        }
    }

    public void releaseConnection(Connection connection) {
        connections.offer(connection);
    }
}
