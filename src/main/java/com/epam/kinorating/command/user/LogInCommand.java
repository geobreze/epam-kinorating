package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.UserService;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LogInCommand implements Command {
    public static final String NAME = "login";

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String ERROR_ATTRIBUTE = "error";
    private static final String INVALID_LOGIN_OR_PASSWORD = "error.login.message";
    private final UserService userService;
    private final URIBuilder uriBuilder;

    public LogInCommand(UserService userService, URIBuilder uriBuilder) {
        this.userService = userService;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        Optional<User> user = userService.login(login, password);

        CommandResult commandResult;
        HttpSession session = request.getSession();
        if (user.isPresent()) {
            session.setAttribute(User.NAME, user.get());
            commandResult = new CommandResult(request.getContextPath(), false);
        } else {
            uriBuilder.setPath(request.getContextPath());
            uriBuilder.addParameter(LOGIN_PARAMETER, login);
            uriBuilder.addParameter(ERROR_ATTRIBUTE, INVALID_LOGIN_OR_PASSWORD);
            commandResult = new CommandResult(uriBuilder.toString(), false);
        }
        return commandResult;
    }
}
