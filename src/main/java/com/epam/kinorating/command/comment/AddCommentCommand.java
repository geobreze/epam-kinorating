package com.epam.kinorating.command.comment;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.command.film.ShowFilmCommand;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Comment;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.service.CommentService;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCommentCommand implements Command {
    public static final String NAME = "add_comment";

    private static final String FILM_ID_PARAMETER = "film_id";
    private static final String TEXT_PARAMETER = "text";
    private static final String ID_PARAMETER = "id";
    private static final String REFERER = "Referer";
    private final CommentService commentService;

    public AddCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.NAME);
        String filmIdString = request.getParameter(FILM_ID_PARAMETER);
        int filmId = Integer.parseInt(filmIdString);
        String text = request.getParameter(TEXT_PARAMETER);
        Comment comment = new Comment(null, user, filmId, text);
        commentService.addNewComment(comment);

        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }

    @Override
    public void close() throws IOException {
        commentService.close();
    }
}
