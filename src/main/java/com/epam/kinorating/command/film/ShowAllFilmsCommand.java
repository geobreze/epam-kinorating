package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllFilmsCommand implements Command {
    public static final String NAME = "show_all";

    private static final String PANEL_PAGE = "/WEB-INF/panel.jsp";
    private static final String FILMS_ATTRIBUTE = "films";
    private final FilmService filmService;

    public ShowAllFilmsCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Film> films = filmService.findAll();
        request.setAttribute(FILMS_ATTRIBUTE, films);
        return new CommandResult(PANEL_PAGE, true);
    }
}
