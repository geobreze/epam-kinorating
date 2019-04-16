package com.epam.kinorating.filter;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.model.entity.User;
import org.apache.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BannedUserFilter implements Filter {
    private static final String FORWARD_URL = "forwardUrl";
    private static final String IGNORED_COMMANDS_DELIMITER = ",";
    private static final String IGNORED_COMMANDS = "ignoredCommands";

    private List<String> ignoredCommands = Collections.emptyList();

    private String forwardUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String commands = filterConfig.getInitParameter(IGNORED_COMMANDS);
        String[] splitCommands = commands.split(IGNORED_COMMANDS_DELIMITER);
        ignoredCommands =  Arrays.asList(splitCommands);
        forwardUrl = filterConfig.getInitParameter(FORWARD_URL);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String command = request.getParameter(Command.NAME);
        User user = (User) session.getAttribute(User.NAME);
        if(user != null && user.isBan() && !ignoredCommands.contains(command)) {
            // TODO: redirect to error page
        } else {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
