package com.epam.kinorating.command.comment;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommentCommand implements Command {
    public static final String NAME = "delete_comment";

    private static final String ID_PARAMETER = "id";
    private static final String REFERER = "Referer";
    private final CommentService commentService;

    public DeleteCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String id = request.getParameter(ID_PARAMETER);
        int commentId = Integer.parseInt(id);
        commentService.removeById(commentId);

        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }
}
