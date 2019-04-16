package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Role;
import com.epam.kinorating.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand implements Command {
    public static final String NAME = "update_user";

    private static final String ID_PARAMETER = "id";
    private static final String ROLE_PARAMETER = "role";
    private static final String BAN_PARAMETER = "ban";
    private static final String BAN_FLAG = "on";
    private static final String REFERER = "Referer";
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter(ID_PARAMETER);
        Integer id = Integer.parseInt(idString);
        String roleString = request.getParameter(ROLE_PARAMETER);
        Role role = Role.valueOf(roleString);
        String banString = request.getParameter(BAN_PARAMETER);
        boolean ban = BAN_FLAG.equals(banString);
        userService.updateRoleAndBanStatus(id, role, ban);

        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }

    @Override
    public void close() throws IOException {
        userService.close();
    }
}
