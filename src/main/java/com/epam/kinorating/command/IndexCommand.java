package com.epam.kinorating.command;

import com.epam.kinorating.exception.NotFoundException;
import com.epam.kinorating.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IndexCommand implements Command {
    public static final String NAME = "index";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String USER_ATTRIBUTE = "user";
    private final Command forwardCommand;

    public IndexCommand(Command forwardCommand) {
        this.forwardCommand = forwardCommand;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, NotFoundException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(USER_ATTRIBUTE);
        CommandResult forwardPath;
        if (user == null) {
            forwardPath = new CommandResult(LOGIN_PAGE, true);
        } else {
            forwardPath = forwardCommand.execute(request, response);
        }
        return forwardPath;
    }
}
