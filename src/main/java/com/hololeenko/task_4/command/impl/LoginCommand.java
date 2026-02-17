package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import com.hololeenko.task_4.exception.CommandException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.entity.UserRole;
import com.hololeenko.task_4.model.service.UserService;
import com.hololeenko.task_4.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.hololeenko.task_4.command.ConstantPagesPath.*;
import static com.hololeenko.task_4.command.ConstantAttribute.*;


public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("Use LoginCommand");
        String login = request.getParameter(AUTHENTICATE_LOGIN);
        String password = request.getParameter(AUTHENTICATE_PASS);

//        UserService userService = UserServiceImpl.getInstance();

        HttpSession session = request.getSession();

        Router router;

        try {
            Optional<User> optionalUser = userService.authenticate(login, password);

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute(USER_LOGIN, user.getLogin());
                session.setAttribute(USER_ROLE, user.getRole());
                session.setAttribute(USER_NAME, user.getName());

                if(user.getRole().equals(UserRole.ADMIN)) {
                    router = new Router(ADMIN_PAGE);
                    router.setRedirect();
                }else{
                    router = new Router(MAIN_PAGE);
                    router.setRedirect();
                }

            }else{
                request.setAttribute(LOGIN_MESSAGE, "Invalid username or password");
                router = new Router(START_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

//        try {
//            if(userService.authenticate(login, password)){
//                session.setAttribute("user_login", login);
//                router = new Router(MAIN_PAGE);
//                router.setRedirect();
//            }else{
//                request.setAttribute("login_msg", "Invalid username or password");
//                router = new Router(START_PAGE);
//            }
//        } catch (ServiceException e) {
//            throw new CommandException(e);
//        }

        return router;
    }
}
