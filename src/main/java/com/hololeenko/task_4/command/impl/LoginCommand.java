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

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static final String MAIN_PAGE = "/pages/main.jsp";
    private static final String ADMIN_PAGE = "/pages/admin.jsp";
    private static final String START_PAGE = "/";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("Use LoginCommand");
        String login = request.getParameter("authenticate_login");//Выносим в константы, а лучше в отдельный класс как все параметры request
        String password = request.getParameter("authenticate_pass");

        UserService userService = UserServiceImpl.getInstance();

        HttpSession session = request.getSession();

        Router router;

        try {
            Optional<User> optionalUser = userService.authenticate(login, password);

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute("user_login", user.getLogin());
                session.setAttribute("user_role", user.getRole());

                if(user.getRole().equals(UserRole.ADMIN)) {
                    router = new Router(ADMIN_PAGE);
                    router.setRedirect();
                }else{
                    router = new Router(MAIN_PAGE);
                    router.setRedirect();
                }

            }else{
                request.setAttribute("login_msg", "Invalid username or password");
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
