package com.epam.kinorating.database;

import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.factory.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int POOL_SIZE = 5;
    private static final ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<ProxyConnection> connections = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool() {
        LOGGER.debug("Creating connection pool");
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initializePool(ConnectionFactory connectionFactory) {
        LOGGER.debug("Initializing connection pool");
        try {
            Class.forName(JDBC_DRIVER);
            for (int i = 0; i < POOL_SIZE; ++i) {
                ProxyConnection connection = connectionFactory.create();
                connections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException("JDBC driver is not found", e);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException("Couldn't create connection", e);
        }
    }

    public ProxyConnection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException("Couldn't get connection from pool", e);
        }
    }

    public void releaseConnection(ProxyConnection connection) {
        connections.offer(connection);
    }

    public void closeAll() {
        for (ProxyConnection connection : connections){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
    }
}
