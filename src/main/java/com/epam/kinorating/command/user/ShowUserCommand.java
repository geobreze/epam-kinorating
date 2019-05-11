package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.NotFoundException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.entity.Role;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowUserCommand implements Command {
    public static final String NAME = "show_user";

    private static final String ID_PARAMETER = "id";
    private static final String SHOW_USER_PAGE = "/WEB-INF/show_user.jsp";
    private static final String ROLES_ATTRIBUTE = "roles";
    private final UserService userService;

    public ShowUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, NotFoundException {
        String idString = request.getParameter(ID_PARAMETER);
        Integer id = Integer.parseInt(idString);
        Optional<User> userOptional = userService.findById(id);

        User user = userOptional.orElseThrow(NotFoundException::new);

        request.setAttribute(User.NAME, user);
        request.setAttribute(ROLES_ATTRIBUTE, Role.values());
        return new CommandResult(SHOW_USER_PAGE, true);
    }
}
