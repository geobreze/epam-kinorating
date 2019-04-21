package com.epam.kinorating.command;

import com.epam.kinorating.exception.NotFoundException;
import com.epam.kinorating.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;

public interface Command {
    String NAME = "command";

    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, NotFoundException;
}
