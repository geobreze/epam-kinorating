package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFilmCommand implements Command {
    public static final String NAME = "delete_film";

    private static final String ID_PARAMETER = "id";
    private static final String INDEX_PAGE = "/";
    private final FilmService filmService;

    public DeleteFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter(ID_PARAMETER);
        int id = Integer.parseInt(idString);
        filmService.removeById(id);

        return new CommandResult(request.getContextPath(), false);
    }

    @Override
    public void close() throws IOException {
        filmService.close();
    }
}
