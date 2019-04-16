package com.epam.kinorating.filter;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.model.entity.Role;
import com.epam.kinorating.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdminAccessFilter implements Filter {
    private static final String ADMIN_COMMANDS_DELIMITER = ",";
    private static final String ADMIN_COMMANDS = "adminCommands";
    private static final String ADMIN_ROLE = "adminRole";

    private List<String> adminCommands = Collections.emptyList();
    private Role adminRole;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String commands = filterConfig.getInitParameter(ADMIN_COMMANDS);
        String[] splitCommands = commands.split(ADMIN_COMMANDS_DELIMITER);
        adminCommands = Arrays.asList(splitCommands);
        String role = filterConfig.getInitParameter(ADMIN_ROLE);
        adminRole = Role.valueOf(role);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.NAME);
        String command = request.getParameter(Command.NAME);
        if(adminCommands.contains(command) && !user.getRole().equals(adminRole)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
