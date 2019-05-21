package com.epam.kinorating.service.utils;

import com.epam.kinorating.database.dao.Dao;
import com.epam.kinorating.entity.Entity;
import com.epam.kinorating.entity.Mark;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.impl.AbstractPageableService;
import com.epam.kinorating.service.impl.FilmServiceImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class PageableLogicTest {
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_ELEMENTS_ON_PAGE = 5;

    @DataProvider
    public static Object[][] pagesCountDataProvider() {
        return new Object[][]{
                new Object[]{2, DEFAULT_ELEMENTS_ON_PAGE, 1},
                new Object[]{10, DEFAULT_ELEMENTS_ON_PAGE, 2},
                new Object[]{10, 6, 2}
        };
    }

    @Test(dataProvider = "pagesCountDataProvider")
    public void countPagesShouldReturnOneWhenLackOfItems(int elementsCount, int elementsOnPage, int expected) throws DaoException, ServiceException {
        Dao<Entity> dao = mock(Dao.class);
        when(dao.getEntriesCount()).thenReturn(elementsCount);
        AbstractPageableService<Film> pageableLogic = new FilmServiceImpl<>(dao);

        int result = pageableLogic.countPages(elementsOnPage);

        assertEquals(result, expected);

        verify(dao).getEntriesCount();
        verifyNoMoreInteractions(dao);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void countPagesShouldThrowServiceExceptionWhenDatabaseIsNotAvailable() throws DaoException, ServiceException {
        Dao<Entity> dao = mock(Dao.class);
        when(dao.getEntriesCount()).thenThrow(DaoException.class);
        PageableLogic<Entity> pageableLogic = new PageableLogic<>(dao);

        pageableLogic.countPages(DEFAULT_ELEMENTS_ON_PAGE);

        verify(dao).getEntriesCount();
        verifyNoMoreInteractions(dao);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllOnPageShouldThrowServiceExceptionWhenDatabaseIsNotAvailable() throws DaoException, ServiceException {
        Dao<Entity> dao = mock(Dao.class);
        when(dao.findAllWithLimitAndOffset(anyInt(), anyInt())).thenThrow(DaoException.class);
        PageableLogic<Entity> pageableLogic = new PageableLogic<>(dao);

        pageableLogic.findAllOnPage(DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE);

        verify(dao).findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, 0);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void findAllOnPageShouldReturnFirstFiveElementsWhenFirstPageSupplied() throws DaoException, ServiceException {
        Dao<Entity> dao = mock(Dao.class);

        List<Entity> testMarks = Arrays.asList(
                new Mark(1, 1, 1, 1),
                new Mark(2, 1, 2, 1),
                new Mark(3, 1, 3, 1),
                new Mark(4, 1, 4, 1),
                new Mark(5, 1, 5, 1)
        );

        when(dao.findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, 0)).thenReturn(testMarks);
        PageableLogic<Entity> pageableLogic = new PageableLogic<>(dao);

        List<Entity> result = pageableLogic.findAllOnPage(DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE);

        assertEquals(result, testMarks);
        verify(dao).findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, 0);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void findAllOnPageShouldReturnLessThenFiveElementsWhenNotFullPageSupplied() throws DaoException, ServiceException {
        Dao<Entity> dao = mock(Dao.class);

        List<Entity> testMarks = Arrays.asList(
                new Mark(1, 1, 1, 1),
                new Mark(2, 1, 2, 1)
        );

        int offset = 20;
        when(dao.findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, offset)).thenReturn(testMarks);
        PageableLogic<Entity> pageableLogic = new PageableLogic<>(dao);

        int page = 5;
        List<Entity> result = pageableLogic.findAllOnPage(page, DEFAULT_ELEMENTS_ON_PAGE);

        assertEquals(result, testMarks);
        verify(dao).findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, offset);
        verifyNoMoreInteractions(dao);
    }
}