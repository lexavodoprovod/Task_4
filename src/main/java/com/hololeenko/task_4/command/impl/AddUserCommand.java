package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import com.hololeenko.task_4.exception.CommandException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.service.UserService;
import com.hololeenko.task_4.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hololeenko.task_4.command.ConstantPagesPath.*;
import static com.hololeenko.task_4.command.ConstantAttribute.*;


public class AddUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    private final UserService userService;

    public AddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("Use AddUserCommand");

        String username = request.getParameter(REGISTER_USER_NAME);
        String login = request.getParameter(REGISTER_LOGIN);
        String password = request.getParameter(REGISTER_PASS);

//        UserService userService = UserServiceImpl.getInstance();

        HttpSession session = request.getSession();

        Router router;

        try{
            if(userService.register(username, login, password)){
                session.setAttribute(USER_NAME, username);
                session.setAttribute(USER_LOGIN, login);

                router = new Router(MAIN_PAGE);
                router.setRedirect();
            }else{
                request.setAttribute(REGISTRATION_MESSAGE, "Registration Failed");
                router = new Router(REGISTRATION_PAGE);
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return router;
    }
}
