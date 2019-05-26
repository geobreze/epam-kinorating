package com.epam.kinorating.service.impl;

import com.epam.kinorating.database.impl.CommentDaoImpl;
import com.epam.kinorating.entity.Comment;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.DaoException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.CommentService;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    private static final CommentDaoImpl EMPTY_COMMENT_DAO_MOCK = mock(CommentDaoImpl.class);
    private static final User user = mock(User.class);
    private static final LocalDateTime date = LocalDateTime.now();
    private final CommentService commentServiceWithEmptyMocks = new CommentServiceImpl(EMPTY_COMMENT_DAO_MOCK);
    private static final Comment CORRECT_COMMENT = new Comment(
            1,
            user,
            1,
            "text",
            date
    );
    private static final Comment EMPTY_COMMENT = new Comment(
            1,
            user,
            1,
            "",
            date
    );


    @Test
    public void saveShouldSaveCorrectComment() throws ServiceException, DaoException {
        commentServiceWithEmptyMocks.save(CORRECT_COMMENT);
        verify(EMPTY_COMMENT_DAO_MOCK).save(CORRECT_COMMENT);
        verifyNoMoreInteractions(EMPTY_COMMENT_DAO_MOCK);
        clearInvocations(EMPTY_COMMENT_DAO_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void saveShouldThrowServiceExceptionWhenFilmWithEmptyTitleSupplied() throws ServiceException {
        commentServiceWithEmptyMocks.save(EMPTY_COMMENT);
        verifyNoMoreInteractions(EMPTY_COMMENT_DAO_MOCK);
    }

}