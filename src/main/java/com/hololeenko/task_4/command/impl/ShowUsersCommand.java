package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import com.hololeenko.task_4.exception.CommandException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.service.UserService;
import com.hololeenko.task_4.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.hololeenko.task_4.command.ConstantPagesPath.*;
import static com.hololeenko.task_4.command.ConstantAttribute.*;



import java.util.List;

public class ShowUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("use ShowUsersCommand");
//        UserService userService = UserServiceImpl.getInstance();
        try {
            List<User> users = userService.getAllUsers();
            request.setAttribute(USER_LIST, users);
            logger.info("Set new requestAttribute user_list");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(ALL_USERS_PAGE);
    }
}
