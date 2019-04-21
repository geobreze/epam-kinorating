package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAddPanelCommand implements Command {
    public static final String NAME = "add_panel";
    private static final String FORWARD_COMMAND_ATTRIBUTE = "forward_command";
    private static final String EDIT_PANEL_PAGE = "/WEB-INF/edit.jsp";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute(FORWARD_COMMAND_ATTRIBUTE, CreateFilmCommand.NAME);
        return new CommandResult(EDIT_PANEL_PAGE, true);
    }
}
