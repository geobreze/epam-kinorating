package com.epam.kinorating.exception;

public class ConnectionFactoryException extends RuntimeException {
    public ConnectionFactoryException() {
    }

    public ConnectionFactoryException(String message) {
        super(message);
    }

    public ConnectionFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionFactoryException(Throwable cause) {
        super(cause);
    }
}
