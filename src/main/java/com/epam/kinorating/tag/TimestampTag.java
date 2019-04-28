package com.epam.kinorating.tag;

import com.epam.kinorating.model.entity.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimestampTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(TimestampTag.class);

    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String EN_PATTERN = "MM/dd/yyyy hh:mm a";
    private static final String RU_PATTERN = "dd MMMM yyyy HH:mm";

    private LocalDateTime date;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String sessionLanguage = (String) session.getAttribute(LANGUAGE_ATTRIBUTE);
        Language language = Language.valueOf(sessionLanguage.toUpperCase());
        Locale locale = Locale.forLanguageTag(language.name());

        String pattern = null;
        switch (language) {
            case RU: {
                pattern = RU_PATTERN;
                break;
            }
            case EN: {
                pattern = EN_PATTERN;
                break;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(pattern)
                .withLocale(locale);

        String formattedDate = date.format(formatter);
        JspWriter out = pageContext.getOut();
        try {
            out.write(formattedDate);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
