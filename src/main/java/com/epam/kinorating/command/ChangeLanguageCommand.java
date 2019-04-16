package com.epam.kinorating.command;

import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.model.database.ConnectionPool;
import com.epam.kinorating.model.entity.Language;
import com.epam.kinorating.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    public static final String NAME = "change_language";

    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String REFERER = "Referer";
    private final Parser<Language> languageParser;

    public ChangeLanguageCommand(Parser<Language> languageParser) {
        this.languageParser = languageParser;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String languageString = request.getParameter(LANGUAGE_ATTRIBUTE);
        Language language = languageParser.parse(languageString);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, language.name().toLowerCase());
        String referer = request.getHeader(REFERER);
        return new CommandResult(referer, false);
    }

    @Override
    public void close() throws IOException {

    }
}
