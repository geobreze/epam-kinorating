package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.NotFoundException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.FilmService;
import com.epam.kinorating.service.MarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowFilmCommand implements Command {
    public static final String NAME = "show";

    private static final String SHOW_PAGE = "/WEB-INF/show.jsp";
    private static final String ID_ATTRIBUTE = "id";
    private static final String USER_MARK = "userMark";
    private final FilmService filmService;
    private final MarkService markService;

    public ShowFilmCommand(FilmService filmService, MarkService markService) {
        this.filmService = filmService;
        this.markService = markService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, NotFoundException {
        String idString = request.getParameter(ID_ATTRIBUTE);
        int filmId = Integer.parseInt(idString);
        Optional<Film> filmOptional = filmService.findByIdWithComments(filmId);

        Film film = filmOptional.orElseThrow(NotFoundException::new);

        request.setAttribute(Film.NAME, film);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.NAME);
        Optional<Mark> markOptional = markService.findMarkByUserAndFilmId(user.getId(), filmId);
        markOptional.ifPresent(mark -> request.setAttribute(USER_MARK, mark));
        return new CommandResult(SHOW_PAGE, true);
    }
}
