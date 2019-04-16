package com.epam.kinorating.controller;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.factory.CommandFactory;
import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.database.ConnectionPool;
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
    private static final String COMMAND_PARAMETER = "command";

    @Override
    public void init() {
        try {
            ConnectionPool.getInstance().initializePool();
        } catch (ConnectionPoolException e) {
            LOGGER.fatal(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Get request: {}", req);
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Post request: {}", req);
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString  = request.getParameter(COMMAND_PARAMETER);
        LOGGER.info("{} command supplied", commandString);
        try (Command command = CommandFactory.create(commandString)) {
            CommandResult forwardPath = command.execute(request, response);
            command.close();
            String forwardUrl = forwardPath.getUrl();
            if(forwardPath.isForward()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardUrl);
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect(forwardUrl);
            }
        } catch (ServiceException | ConnectionPoolException e) {
            LOGGER.error(e);
            // redirect error page
        }
    }
}
