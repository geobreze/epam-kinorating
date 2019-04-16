package com.epam.kinorating.command.mark;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.service.MarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddMarkCommand implements Command {
    public static final String NAME = "add_mark";

    private static final String FILM_ID_ATTRIBUTE = "film_id";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String REFERER = "Referer";
    private final MarkService markService;

    public AddMarkCommand(MarkService markService) {
        this.markService = markService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.NAME);
        String filmIdString = request.getParameter(FILM_ID_ATTRIBUTE);
        int filmId = Integer.valueOf(filmIdString);
        String valueString = request.getParameter(VALUE_ATTRIBUTE);
        int value = Integer.valueOf(valueString);
        Mark mark = new Mark(null, value, filmId, user.getId());
        markService.addMark(mark);

        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }

    @Override
    public void close() throws IOException {
        markService.close();
    }
}
