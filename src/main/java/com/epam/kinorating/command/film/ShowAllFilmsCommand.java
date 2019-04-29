package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Film;
import com.epam.kinorating.service.FilmService;
import org.apache.commons.validator.routines.IntegerValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllFilmsCommand implements Command {
    public static final String NAME = "show_all";

    private static final int ELEMENTS_ON_PAGE = 5;
    private static final int DEFAULT_PAGE = 1;

    private static final String PANEL_PAGE = "/WEB-INF/panel.jsp";
    private static final String FILMS_ATTRIBUTE = "films";
    private static final String PAGES_ATTRIBUTE = "pages";
    private static final String CURRENT_PAGE_ATTRIBUTE = "current_page";
    private final FilmService filmService;
    private final IntegerValidator integerValidator;

    public ShowAllFilmsCommand(FilmService filmService, IntegerValidator integerValidator) {
        this.filmService = filmService;
        this.integerValidator = integerValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int pages = filmService.countPages(ELEMENTS_ON_PAGE);
        request.setAttribute(PAGES_ATTRIBUTE, pages);
        String currentPageString = request.getParameter(CURRENT_PAGE_ATTRIBUTE);
        Integer currentPage = integerValidator.validate(currentPageString);

        if(currentPage == null || !filmService.validatePage(pages, currentPage)) {
            currentPage = DEFAULT_PAGE;
        }
        request.setAttribute(CURRENT_PAGE_ATTRIBUTE, currentPage);

        List<Film> films = filmService.findAllOnPage(currentPage, ELEMENTS_ON_PAGE);
        request.setAttribute(FILMS_ATTRIBUTE, films);

        return new CommandResult(PANEL_PAGE, true);
    }

}
