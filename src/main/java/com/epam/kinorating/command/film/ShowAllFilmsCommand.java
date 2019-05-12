package com.epam.kinorating.command.film;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.command.utils.PageableContentCommandHelper;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.entity.Film;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllFilmsCommand implements Command {
    public static final String NAME = "show_all";

    private static final int ELEMENTS_ON_PAGE = 5;

    private static final String PANEL_PAGE = "/WEB-INF/panel.jsp";
    private static final String FILMS_ATTRIBUTE = "films";
    private final PageableContentCommandHelper<Film> pageableContentCommandHelper;

    public ShowAllFilmsCommand(PageableContentCommandHelper<Film> pageableContentCommandHelper) {
        this.pageableContentCommandHelper = pageableContentCommandHelper;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int pages = pageableContentCommandHelper.countAndSetPagesAttribute(request, ELEMENTS_ON_PAGE);
        int currentPage = pageableContentCommandHelper.parseAndSetCurrentPage(request, pages);
        pageableContentCommandHelper.findAndSetElementsOnPage(request, currentPage, ELEMENTS_ON_PAGE, FILMS_ATTRIBUTE);

        return new CommandResult(PANEL_PAGE, true);
    }

}
