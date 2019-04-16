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
import com.epam.kinorating.exception.ConnectionPoolException;
import com.epam.kinorating.model.entity.*;
import com.epam.kinorating.parser.LanguageParser;
import com.epam.kinorating.parser.Parser;
import com.epam.kinorating.service.CommentService;
import com.epam.kinorating.service.FilmService;
import com.epam.kinorating.service.MarkService;
import com.epam.kinorating.service.UserService;
import org.apache.http.client.utils.URIBuilder;

public class CommandFactory {

    public static Command create(String command) throws ConnectionPoolException {
        Command commandObject;
        if (command == null) {
            Command showAllFilmsCommand = CommandFactory.create(ShowAllFilmsCommand.NAME);
            return new IndexCommand(showAllFilmsCommand);
        }
        switch (command) {
            case LogInCommand.NAME: {
                UserService userService = (UserService) ServiceFactory.create(User.NAME);
                URIBuilder uriBuilder = new URIBuilder();
                commandObject = new LogInCommand(userService, uriBuilder);
                break;
            }
            case LogOutCommand.NAME: {
                commandObject = new LogOutCommand();
                break;
            }
            case ShowAllFilmsCommand.NAME: {
                FilmService filmService = (FilmService) ServiceFactory.create(Film.NAME);
                commandObject = new ShowAllFilmsCommand(filmService);
                break;
            }
            case ShowFilmCommand.NAME: {
                FilmService filmService = (FilmService) ServiceFactory.create(Film.NAME);
                MarkService markService = (MarkService) ServiceFactory.create(Mark.NAME);
                commandObject = new ShowFilmCommand(filmService, markService);
                break;
            }
            case DeleteCommentCommand.NAME: {
                CommentService commentService = (CommentService) ServiceFactory.create(Comment.NAME);
                commandObject = new DeleteCommentCommand(commentService);
                break;
            }
            case CreateFilmCommand.NAME: {
                FilmService filmService = (FilmService) ServiceFactory.create(Film.NAME);
                commandObject = new CreateFilmCommand(filmService);
                break;
            }
            case AddMarkCommand.NAME: {
                MarkService markService = (MarkService) ServiceFactory.create(Mark.NAME);
                commandObject = new AddMarkCommand(markService);
                break;
            }
            case RemoveMarkCommand.NAME: {
                MarkService markService = (MarkService) ServiceFactory.create(Mark.NAME);
                commandObject = new RemoveMarkCommand(markService);
                break;
            }
            case ShowAddPanelCommand.NAME: {
                commandObject = new ShowAddPanelCommand();
                break;
            }
            case ShowEditPanelCommand.NAME: {
                FilmService filmService = (FilmService) ServiceFactory.create(Film.NAME);
                commandObject = new ShowEditPanelCommand(filmService);
                break;
            }
            case EditFilmCommand.NAME: {
                FilmService filmService = (FilmService) ServiceFactory.create(Film.NAME);
                URIBuilder uriBuilder = new URIBuilder();
                commandObject = new EditFilmCommand(filmService, uriBuilder);
                break;
            }
            case DeleteFilmCommand.NAME: {
                FilmService filmService = (FilmService) ServiceFactory.create(Film.NAME);
                commandObject = new DeleteFilmCommand(filmService);
                break;
            }
            case AddCommentCommand.NAME: {
                CommentService commentService = (CommentService) ServiceFactory.create(Comment.NAME);
                commandObject = new AddCommentCommand(commentService);
                break;
            }
            case UpdateUserCommand.NAME: {
                UserService userService = (UserService) ServiceFactory.create(User.NAME);
                commandObject = new UpdateUserCommand(userService);
                break;
            }
            case ShowUserListCommand.NAME: {
                UserService userService = (UserService) ServiceFactory.create(User.NAME);
                commandObject = new ShowUserListCommand(userService);
                break;
            }
            case ShowUserCommand.NAME: {
                UserService userService = (UserService) ServiceFactory.create(User.NAME);
                commandObject = new ShowUserCommand(userService);
                break;
            }
            case ChangeLanguageCommand.NAME: {
                Parser<Language> languageParser = new LanguageParser();
                commandObject = new ChangeLanguageCommand(languageParser);
                break;
            }
            default: {
                Command showAllFilmsCommand = CommandFactory.create(ShowAllFilmsCommand.NAME);
                commandObject = new IndexCommand(showAllFilmsCommand);
                break;
            }
        }
        return commandObject;
    }

}
