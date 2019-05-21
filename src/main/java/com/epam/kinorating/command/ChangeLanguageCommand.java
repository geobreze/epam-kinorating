package com.epam.kinorating.command;

import com.epam.kinorating.entity.Language;
import com.epam.kinorating.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    public static final String NAME = "change_language";

    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String REFERER = "Referer";

    public ChangeLanguageCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String languageString = request.getParameter(LANGUAGE_ATTRIBUTE);
        Language language = Language.parse(languageString);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, language.name().toLowerCase());
        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }
}
