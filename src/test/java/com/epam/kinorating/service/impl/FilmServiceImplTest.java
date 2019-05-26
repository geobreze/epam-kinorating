package com.epam.kinorating.service.impl;

import com.epam.kinorating.database.impl.FilmDaoImpl;
import com.epam.kinorating.entity.Film;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.CommentService;
import com.epam.kinorating.service.FilmService;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class FilmServiceImplTest {

    private static final FilmDaoImpl EMPTY_FILM_DAO_MOCK = mock(FilmDaoImpl.class);
    private static final CommentService EMPTY_COMMENT_SERVICE_MOCK = mock(CommentService.class);
    private final FilmService filmServiceWithEmptyMocks = new FilmServiceImpl(EMPTY_FILM_DAO_MOCK,
            EMPTY_COMMENT_SERVICE_MOCK);
    private static final Film CORRECT_FILM = new Film(
            1,
            "Title",
            "Description",
            1
    );
    private static final Film EMPTY_TITLE_FILM = new Film(
            1,
            "",
            "Description",
            1
    );
    private static final Film EMPTY_DESCRIPTION_FILM = new Film(
            1,
            "Title",
            "",
            1
    );
    private static final Film LONG_TITLE_FILM = new Film(
            1,
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Description",
            1
    );


    @Test
    public void saveShouldSaveCorrectFilm() throws ServiceException, DaoException {
        filmServiceWithEmptyMocks.save(CORRECT_FILM);
        verify(EMPTY_FILM_DAO_MOCK).save(CORRECT_FILM);
        verifyNoMoreInteractions(EMPTY_FILM_DAO_MOCK, EMPTY_COMMENT_SERVICE_MOCK);
        clearInvocations(EMPTY_FILM_DAO_MOCK, EMPTY_COMMENT_SERVICE_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void saveShouldThrowServiceExceptionWhenFilmWithEmptyTitleSupplied() throws ServiceException {
        filmServiceWithEmptyMocks.save(EMPTY_TITLE_FILM);
        verifyNoMoreInteractions(EMPTY_FILM_DAO_MOCK, EMPTY_COMMENT_SERVICE_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void saveShouldThrowServiceExceptionWhenFilmWithEmptyDescriptionSupplied() throws ServiceException {
        filmServiceWithEmptyMocks.save(EMPTY_DESCRIPTION_FILM);
        verifyNoMoreInteractions(EMPTY_FILM_DAO_MOCK, EMPTY_COMMENT_SERVICE_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void saveShouldThrowServiceExceptionWhenFilmWithLongTitleSupplied() throws ServiceException {
        filmServiceWithEmptyMocks.save(LONG_TITLE_FILM);
        verifyNoMoreInteractions(EMPTY_FILM_DAO_MOCK, EMPTY_COMMENT_SERVICE_MOCK);
    }

}