package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.FilmService;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditFilmCommand implements Command {
    public static final String NAME = "edit_film";

    private static final String ID_PARAMETER = "id";
    private static final String TITLE_PARAMETER = "title";
    private static final String DESCRIPTION_PARAMETER = "description";
    private final FilmService filmService;
    private final URIBuilder uriBuilder;

    public EditFilmCommand(FilmService filmService, URIBuilder uriBuilder) {
        this.filmService = filmService;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter(ID_PARAMETER);
        int id = Integer.parseInt(idString);
        String title = request.getParameter(TITLE_PARAMETER);
        String description = request.getParameter(DESCRIPTION_PARAMETER);
        Film film = new Film(id, title, description, 0);
        filmService.save(film);

        uriBuilder.setPath(request.getContextPath());
        uriBuilder.addParameter(ID_PARAMETER, idString);
        uriBuilder.addParameter(Command.NAME, ShowFilmCommand.NAME);
        return new CommandResult(uriBuilder.toString(), false);
    }
}
