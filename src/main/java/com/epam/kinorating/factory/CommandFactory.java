package com.epam.kinorating.factory;

import com.epam.kinorating.command.ChangeLanguageCommand;
import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.IndexCommand;
import com.epam.kinorating.command.comment.AddCommentCommand;
import com.epam.kinorating.command.comment.DeleteCommentCommand;
import com.epam.kinorating.command.film.*;
import com.epam.kinorating.command.mark.AddMarkCommand;
import com.epam.kinorating.command.mark.RemoveMarkCommand;
import com.epam.kinorating.command.user.*;
import com.epam.kinorating.service.CommentService;
import com.epam.kinorating.service.FilmService;
import com.epam.kinorating.service.MarkService;
import com.epam.kinorating.service.UserService;
import com.epam.kinorating.service.utils.PaginationHelper;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.http.client.utils.URIBuilder;

public class CommandFactory {
    private final ServiceFactory serviceFactory;
    private final PaginationHelper paginationHelper;

    public CommandFactory(ServiceFactory serviceFactory, PaginationHelper paginationHelper) {
        this.serviceFactory = serviceFactory;
        this.paginationHelper = paginationHelper;
    }

    public Command create(String command) {
        Command commandObject;
        if (command == null) {
            Command showAllFilmsCommand = create(ShowAllFilmsCommand.NAME);
            return new IndexCommand(showAllFilmsCommand);
        }
        switch (command) {
            case LogInCommand.NAME: {
                UserService userService = serviceFactory.createUserService();
                URIBuilder uriBuilder = new URIBuilder();
                commandObject = new LogInCommand(userService, uriBuilder);
                break;
            }
            case LogOutCommand.NAME: {
                commandObject = new LogOutCommand();
                break;
            }
            case ShowAllFilmsCommand.NAME: {
                FilmService filmService = serviceFactory.createFilmService();
                IntegerValidator integerValidator = IntegerValidator.getInstance();
                commandObject = new ShowAllFilmsCommand(filmService, integerValidator, paginationHelper);
                break;
            }
            case ShowFilmCommand.NAME: {
                FilmService filmService = serviceFactory.createFilmService();
                MarkService markService = serviceFactory.createMarkService();
                commandObject = new ShowFilmCommand(filmService, markService);
                break;
            }
            case DeleteCommentCommand.NAME: {
                CommentService commentService = serviceFactory.createCommentService();
                commandObject = new DeleteCommentCommand(commentService);
                break;
            }
            case CreateFilmCommand.NAME: {
                FilmService filmService = serviceFactory.createFilmService();
                commandObject = new CreateFilmCommand(filmService);
                break;
            }
            case AddMarkCommand.NAME: {
                MarkService markService = serviceFactory.createMarkService();
                commandObject = new AddMarkCommand(markService);
                break;
            }
            case RemoveMarkCommand.NAME: {
                MarkService markService = serviceFactory.createMarkService();
                commandObject = new RemoveMarkCommand(markService);
                break;
            }
            case ShowAddPanelCommand.NAME: {
                commandObject = new ShowAddPanelCommand();
                break;
            }
            case ShowEditPanelCommand.NAME: {
                FilmService filmService = serviceFactory.createFilmService();
                commandObject = new ShowEditPanelCommand(filmService);
                break;
            }
            case EditFilmCommand.NAME: {
                FilmService filmService = serviceFactory.createFilmService();
                URIBuilder uriBuilder = new URIBuilder();
                commandObject = new EditFilmCommand(filmService, uriBuilder);
                break;
            }
            case DeleteFilmCommand.NAME: {
                FilmService filmService = serviceFactory.createFilmService();
                commandObject = new DeleteFilmCommand(filmService);
                break;
            }
            case AddCommentCommand.NAME: {
                CommentService commentService = serviceFactory.createCommentService();
                commandObject = new AddCommentCommand(commentService);
                break;
            }
            case UpdateUserCommand.NAME: {
                UserService userService = serviceFactory.createUserService();
                commandObject = new UpdateUserCommand(userService);
                break;
            }
            case ShowUserListCommand.NAME: {
                UserService userService = serviceFactory.createUserService();
                commandObject = new ShowUserListCommand(userService);
                break;
            }
            case ShowUserCommand.NAME: {
                UserService userService = serviceFactory.createUserService();
                commandObject = new ShowUserCommand(userService);
                break;
            }
            case ChangeLanguageCommand.NAME: {
                commandObject = new ChangeLanguageCommand();
                break;
            }
            default: {
                Command showAllFilmsCommand = create(ShowAllFilmsCommand.NAME);
                commandObject = new IndexCommand(showAllFilmsCommand);
                break;
            }
        }
        return commandObject;
    }

}
