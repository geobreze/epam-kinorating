package com.epam.kinorating.controller;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.NotFoundException;
import com.epam.kinorating.factory.*;
import com.epam.kinorating.model.database.ConnectionPool;
import com.epam.kinorating.model.database.ProxyConnection;
import com.epam.kinorating.model.database.utils.Hasher;
import com.epam.kinorating.model.database.utils.SHA256Hasher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);
    private static final String DATABASE_CONFIG = "database";
    private static final int NOT_FOUND_ERROR = 404;
    private static final int SERVER_ERROR = 500;

    @Override
    public void init() {
        ConnectionFactory connectionFactory = new ConnectionFactory(DATABASE_CONFIG);
        ConnectionPool.getInstance().initializePool(connectionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(Command.NAME);
        LOGGER.info("{} command supplied", commandString);
        ProxyConnection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            CommandFactory commandFactory = createCommandFactory(connection);
            Command command = commandFactory.create(commandString);
            CommandResult commandResult = command.execute(request, response);
            doRoute(request, response, commandResult);
        } catch (NotFoundException e) {
            LOGGER.error(e);
            response.sendError(NOT_FOUND_ERROR);
        } catch (Exception e) {
            LOGGER.error(e);
            response.sendError(SERVER_ERROR);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private void doRoute(HttpServletRequest request, HttpServletResponse response, CommandResult commandResult) throws ServletException, IOException {
        String forwardUrl = commandResult.getUrl();
        if (commandResult.isForward()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardUrl);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect(forwardUrl);
        }
    }

    private CommandFactory createCommandFactory(ProxyConnection connection) {
        BuilderFactory builderFactory = new BuilderFactory();
        Hasher hasher = new SHA256Hasher();
        DaoFactory daoFactory = new DaoFactory(connection, builderFactory, hasher);
        ServiceFactory serviceFactory = new ServiceFactory(daoFactory);
        LanguageFactory languageFactory = new LanguageFactory();
        return new CommandFactory(serviceFactory, languageFactory);
    }


    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().close();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}