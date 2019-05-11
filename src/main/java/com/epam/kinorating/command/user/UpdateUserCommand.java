package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.entity.Status;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UpdateUserCommand implements Command {
    public static final String NAME = "update_user";

    private static final String ID_PARAMETER = "id";
    private static final String STATUS_PARAMETER = "status";
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

        Map<String, String[]> parameterMap = request.getParameterMap();

        if(parameterMap.containsKey(STATUS_PARAMETER)) {
            getStatusFromRequestAndUpdate(id, request);
        }
        if(parameterMap.containsKey(BAN_PARAMETER)) {
            getBanFromRequestAndUpdate(id, request);
        }

        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }

    private void getStatusFromRequestAndUpdate(Integer id, HttpServletRequest request) throws ServiceException {
        String statusString = request.getParameter(STATUS_PARAMETER);
        Status status = Status.valueOf(statusString);
        userService.updateStatus(id, status);
    }

    private void getBanFromRequestAndUpdate(Integer id, HttpServletRequest request) throws ServiceException {
        String banString = request.getParameter(BAN_PARAMETER);
        boolean ban = BAN_FLAG.equals(banString);
        userService.updateBan(id, ban);
    }
}
