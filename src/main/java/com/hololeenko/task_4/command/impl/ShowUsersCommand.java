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

import java.util.List;

public class ShowUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static final String ALL_USERS_PAGE = "/pages/all_users.jsp";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("use ShowUsersCommand");
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<User> users = userService.getAllUsers();
            request.setAttribute("user_list", users);
            logger.info("Set new requestAttribute user_list");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        Router router = new Router(ALL_USERS_PAGE);
        return router;
    }
}
