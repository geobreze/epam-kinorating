package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowEditPanelCommand implements Command {
    public static final String NAME = "edit_panel";

    private static final String ID_PARAMETER = "id";
    private static final String FORWARD_COMMAND_ATTRIBUTE = "forward_command";
    private static final String EDIT_PANEL_PAGE = "/WEB-INF/edit.jsp";
    private final FilmService filmService;

    public ShowEditPanelCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter(ID_PARAMETER);
        int id = Integer.parseInt(idString);
        Optional<Film> filmOptional = filmService.findById(id);
        filmOptional.ifPresent(film -> request.setAttribute(Film.NAME, film));
        request.setAttribute(FORWARD_COMMAND_ATTRIBUTE, EditFilmCommand.NAME);
        return new CommandResult(EDIT_PANEL_PAGE, true);
    }
}
