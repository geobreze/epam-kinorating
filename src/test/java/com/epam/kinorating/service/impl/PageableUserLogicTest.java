package com.epam.kinorating.service.impl;

import com.epam.kinorating.database.impl.UserDaoImpl;
import com.epam.kinorating.entity.*;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.Pageable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class PageableUserLogicTest {
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
        UserDaoImpl dao = mock(UserDaoImpl.class);
        when(dao.getEntriesCount()).thenReturn(elementsCount);
        Pageable<User> pageableLogic = new UserServiceImpl(dao);

        int result = pageableLogic.countPages(elementsOnPage);

        assertEquals(result, expected);

        verify(dao).getEntriesCount();
        verifyNoMoreInteractions(dao);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void countPagesShouldThrowServiceExceptionWhenDatabaseIsNotAvailable() throws DaoException, ServiceException {
        UserDaoImpl dao = mock(UserDaoImpl.class);
        when(dao.getEntriesCount()).thenThrow(DaoException.class);
        Pageable<User> pageableLogic = new UserServiceImpl(dao);

        pageableLogic.countPages(DEFAULT_ELEMENTS_ON_PAGE);

        verify(dao).getEntriesCount();
        verifyNoMoreInteractions(dao);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllOnPageShouldThrowServiceExceptionWhenDatabaseIsNotAvailable() throws DaoException, ServiceException {
        UserDaoImpl dao = mock(UserDaoImpl.class);
        when(dao.findAllWithLimitAndOffset(anyInt(), anyInt())).thenThrow(DaoException.class);
        Pageable<User> pageableLogic = new UserServiceImpl(dao);

        pageableLogic.findAllOnPage(DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE);

        verify(dao).findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, 0);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void findAllOnPageShouldReturnFirstFiveElementsWhenFirstPageSupplied() throws DaoException, ServiceException {
        UserDaoImpl dao = mock(UserDaoImpl.class);

        List<User> testUsers = Arrays.asList(
                new User(1, "1", "1", Role.ADMIN, true, Status.REGULAR),
                new User(2, "2", "1", Role.USER, false, Status.GOLD),
                new User(3, "3", "1", Role.ADMIN, false, Status.REGULAR),
                new User(4, "4", "1", Role.USER, true, Status.VIP),
                new User(5, "5", "1", Role.ADMIN, false, Status.REGULAR),
                new User(6, "6", "1", Role.USER, false, Status.REGULAR)
        );

        when(dao.findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, 0)).thenReturn(testUsers);
        Pageable<User> pageableLogic = new UserServiceImpl(dao);

        List<User> result = pageableLogic.findAllOnPage(DEFAULT_CURRENT_PAGE, DEFAULT_ELEMENTS_ON_PAGE);

        assertEquals(result, testUsers);
        verify(dao).findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, 0);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void findAllOnPageShouldReturnLessThenFiveElementsWhenNotFullPageSupplied() throws DaoException, ServiceException {
        UserDaoImpl dao = mock(UserDaoImpl.class);

        List<User> testUsers = Arrays.asList(
                new User(1, "1", "1", Role.ADMIN, true, Status.REGULAR),
                new User(2, "2", "1", Role.USER, false, Status.GOLD),
                new User(3, "3", "1", Role.ADMIN, false, Status.REGULAR)
        );

        int offset = 20;
        when(dao.findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, offset)).thenReturn(testUsers);
        Pageable<User> pageableLogic = new UserServiceImpl(dao);

        int page = 5;
        List<User> result = pageableLogic.findAllOnPage(page, DEFAULT_ELEMENTS_ON_PAGE);

        assertEquals(result, testUsers);
        verify(dao).findAllWithLimitAndOffset(DEFAULT_ELEMENTS_ON_PAGE, offset);
        verifyNoMoreInteractions(dao);
    }
}