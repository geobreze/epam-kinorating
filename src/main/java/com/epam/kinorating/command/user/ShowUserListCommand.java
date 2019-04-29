package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserListCommand implements Command {
    public static final String NAME = "show_users";

    private static final String USERS_ATTRIBUTE = "users";
    private static final String SHOW_USERS_PAGE = "/WEB-INF/user_list.jsp";
    private final UserService userService;

    public ShowUserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = userService.getAll();
        request.setAttribute(USERS_ATTRIBUTE, users);
        return new CommandResult(SHOW_USERS_PAGE, true);
    }
}
