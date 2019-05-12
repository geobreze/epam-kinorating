package com.epam.kinorating.command.utils;

import com.epam.kinorating.entity.Entity;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.Pageable;
import com.epam.kinorating.service.utils.PaginationHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class PageableContentCommandHelperTest {

    private static final int DEFAULT_PAGES_COUNT = 10;
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_ELEMENTS_ON_PAGE = 10;
    private static final String DEFAULT_ATTRIBUTE_NAME = "entities";
    private static final String DEFAULT_CURRENT_PAGE_STRING = "1";
    private static final String PAGES_ATTRIBUTE = "pages";
    private static final String CURRENT_PAGE_ATTRIBUTE = "current_page";

    @Test
    public void countAndSetPagesAttributeTestOnDefaultData() throws ServiceException {
        Pageable<Entity> pageable = mock(Pageable.class);
        when(pageable.countPages(DEFAULT_ELEMENTS_ON_PAGE)).thenReturn(DEFAULT_PAGES_COUNT);
        PaginationHelper paginationHelper = mock(PaginationHelper.class);

        PageableContentCommandHelper<Entity> pageableContentCommandHelper = new PageableContentCommandHelper<>(pageable, paginationHelper);

        HttpServletRequest request = mock(HttpServletRequest.class);
        int result = pageableContentCommandHelper.countAndSetPagesAttribute(request, DEFAULT_ELEMENTS_ON_PAGE);

        Assert.assertEquals(result, DEFAULT_PAGES_COUNT);
        verify(pageable).countPages(DEFAULT_ELEMENTS_ON_PAGE);
        verify(request).setAttribute(PAGES_ATTRIBUTE, DEFAULT_PAGES_COUNT);
        verifyNoMoreInteractions(request, pageable, paginationHelper);
    }

    @Test
    public void parseAndSetCurrentPageTestOnDefaultData() {
        PaginationHelper paginationHelper = mock(PaginationHelper.class);
        when(paginationHelper.parsePageOrDefault(DEFAULT_CURRENT_PAGE_STRING, DEFAULT_PAGES_COUNT)).thenReturn(DEFAULT_CURRENT_PAGE);

        Pageable<Entity> pageable = mock(Pageable.class);
        PageableContentCommandHelper<Entity> pageableContentCommandHelper = new PageableContentCommandHelper<>(pageable, paginationHelper);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(CURRENT_PAGE_ATTRIBUTE)).thenReturn(DEFAULT_CURRENT_PAGE_STRING);
        int result = pageableContentCommandHelper.parseAndSetCurrentPage(request, DEFAULT_PAGES_COUNT);

        Assert.assertEquals(result, DEFAULT_CURRENT_PAGE);
        verify(request).getParameter(CURRENT_PAGE_ATTRIBUTE);
        verify(request).setAttribute(CURRENT_PAGE_ATTRIBUTE, DEFAULT_CURRENT_PAGE);
        verify(paginationHelper).parsePageOrDefault(DEFAULT_CURRENT_PAGE_STRING, DEFAULT_PAGES_COUNT);
        verifyNoMoreInteractions(request, pageable, paginationHelper);
    }

    @Test
    public void findAndSetElementsOnPageShouldSetEmptyList() throws ServiceException {
        PaginationHelper paginationHelper = mock(PaginationHelper.class);
        Pageable<Entity> pageable = mock(Pageable.class);
        when(pageable.findAllOnPage(DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE)).thenReturn(Collections.emptyList());

        PageableContentCommandHelper<Entity> pageableContentCommandHelper = new PageableContentCommandHelper<>(pageable, paginationHelper);
        HttpServletRequest request = mock(HttpServletRequest.class);
        pageableContentCommandHelper.findAndSetElementsOnPage(request, DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE, DEFAULT_ATTRIBUTE_NAME);

        verify(request).setAttribute(DEFAULT_ATTRIBUTE_NAME, Collections.emptyList());
        verify(pageable).findAllOnPage(DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE);
        verifyNoMoreInteractions(request, pageable, paginationHelper);
    }
}