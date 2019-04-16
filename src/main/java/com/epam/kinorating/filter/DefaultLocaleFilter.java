package com.epam.kinorating.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DefaultLocaleFilter implements Filter {

    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String DEFAULT_LANGUAGE = "defaultLanguage";
    private String defaultLanguage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLanguage = filterConfig.getInitParameter(DEFAULT_LANGUAGE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object locale = session.getAttribute(LANGUAGE_ATTRIBUTE);
        if(locale == null) {
            session.setAttribute(LANGUAGE_ATTRIBUTE, defaultLanguage);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
