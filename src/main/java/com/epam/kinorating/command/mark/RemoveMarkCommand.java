package com.epam.kinorating.command.mark;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.command.film.ShowFilmCommand;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.entity.Mark;
import com.epam.kinorating.model.entity.User;
import com.epam.kinorating.service.MarkService;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveMarkCommand implements Command {
    public static final String NAME = "remove_mark";

    private static final String USER_ATTRIBUTE = "user";
    private static final String FILM_ID_ATTRIBUTE = "film_id";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String COMMAND_LABEL = "command";
    private static final String SHOW_COMMAND = "show";
    private static final String ID_ATTRIBUTE = "id";
    private static final String REFERER = "Referer";
    private final MarkService markService;
    private final URIBuilder uriBuilder;

    public RemoveMarkCommand(MarkService markService, URIBuilder uriBuilder) {
        this.markService = markService;
        this.uriBuilder = uriBuilder;
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

    @Override
    public void close() throws IOException {
        markService.close();
    }
}
