package com.epam.kinorating.command.utils;

import com.epam.kinorating.entity.Entity;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.Pageable;
import com.epam.kinorating.service.utils.PaginationHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageableContentCommandHelper<T extends Entity> {

    private static final String PAGES_ATTRIBUTE = "pages";
    private static final String CURRENT_PAGE_ATTRIBUTE = "current_page";

    private final Pageable<T> pageable;
    private final PaginationHelper paginationHelper;

    public PageableContentCommandHelper(Pageable<T> pageable, PaginationHelper paginationHelper) {
        this.pageable = pageable;
        this.paginationHelper = paginationHelper;
    }

    public int countAndSetPagesAttribute(HttpServletRequest request, int elementsOnPage) throws ServiceException {
        int pages = pageable.countPages(elementsOnPage);
        request.setAttribute(PAGES_ATTRIBUTE, pages);
        return pages;
    }

    public int parseAndSetCurrentPage(HttpServletRequest request, int pages) {
        String currentPageString = request.getParameter(CURRENT_PAGE_ATTRIBUTE);
        int currentPage = paginationHelper.parsePageOrDefault(currentPageString, pages);
        request.setAttribute(CURRENT_PAGE_ATTRIBUTE, currentPage);
        return currentPage;
    }

    public void findAndSetElementsOnPage(HttpServletRequest request, int currentPage, int elementsOnPage, String attributeName)
            throws ServiceException {
        List<T> users = pageable.findAllOnPage(currentPage, elementsOnPage);
        request.setAttribute(attributeName, users);
    }
}
