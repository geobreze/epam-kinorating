package com.epam.kinorating.command.mark;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.service.MarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveMarkCommand implements Command {
    public static final String NAME = "remove_mark";

    private static final String FILM_ID_ATTRIBUTE = "film_id";
    private static final String REFERER = "Referer";
    private final MarkService markService;

    public RemoveMarkCommand(MarkService markService) {
        this.markService = markService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdString = request.getParameter(FILM_ID_ATTRIBUTE);
        int filmId = Integer.valueOf(filmIdString);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.NAME);
        markService.removeMark(user.getId(), filmId);

        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }
}
