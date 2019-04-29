package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFilmCommand implements Command {
    public static final String NAME = "create_film";

    private static final String TITLE_ATTRIBUTE = "title";
    private static final String DESCRIPTION_ATTRIBUTE = "description";
    private final FilmService filmService;

    public CreateFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter(TITLE_ATTRIBUTE);
        String description = request.getParameter(DESCRIPTION_ATTRIBUTE);
        Film film = new Film(null, title, description, 0);
        filmService.saveFilm(film);

        return new CommandResult(request.getContextPath(), false);
    }
}
