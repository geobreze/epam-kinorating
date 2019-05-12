package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.CommandResult;
import com.epam.kinorating.command.utils.PageableContentCommandHelper;
import com.epam.kinorating.entity.Status;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserListCommand implements Command {
    public static final String NAME = "show_users";

    private static final int ELEMENTS_ON_PAGE = 5;

    private static final String USERS_ATTRIBUTE = "users";
    private static final String SHOW_USERS_PAGE = "/WEB-INF/user_list.jsp";
    private static final String STATUSES_ATTRIBUTE = "statuses";
    private final PageableContentCommandHelper<User> pageableContentCommandHelper;

    public ShowUserListCommand(PageableContentCommandHelper<User> pageableContentCommandHelper) {
        this.pageableContentCommandHelper = pageableContentCommandHelper;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int pages = pageableContentCommandHelper.countAndSetPagesAttribute(request, ELEMENTS_ON_PAGE);
        int currentPage = pageableContentCommandHelper.parseAndSetCurrentPage(request, pages);
        pageableContentCommandHelper.findAndSetElementsOnPage(request, currentPage, ELEMENTS_ON_PAGE, USERS_ATTRIBUTE);

        request.setAttribute(STATUSES_ATTRIBUTE, Status.values());
        return new CommandResult(SHOW_USERS_PAGE, true);
    }
}
