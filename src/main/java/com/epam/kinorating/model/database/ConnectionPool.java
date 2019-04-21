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
    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool() {
        LOGGER.info("Creating connection pool");
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initializePool(ConnectionFactory connectionFactory) {
        LOGGER.info("Initializing connection pool");
        try {
            Class.forName(JDBC_DRIVER);
            for (int i = 0; i < POOL_SIZE; ++i) {
                Connection connection = connectionFactory.create();
                connections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("JDBC driver is not found", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Couldn't create connection", e);
        }
    }

    public Connection getConnection() {
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
