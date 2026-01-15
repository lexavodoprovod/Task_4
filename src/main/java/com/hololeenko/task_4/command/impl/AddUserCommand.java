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

public class AddUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);


    private static final String REGISTRATION_PAGE = "pages/register.jsp";
    private static final String MAIN_PAGE = "pages/main.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("Use AddUserCommand");

        String username = request.getParameter("register_userName");
        String login = request.getParameter("register_login");
        String password = request.getParameter("register_pass");

        UserService userService = UserServiceImpl.getInstance();

        HttpSession session = request.getSession();

        Router router;

        try{
            if(userService.register(username, login, password)){
                session.setAttribute("user_name", username);
                session.setAttribute("user_login", login);

                router = new Router(MAIN_PAGE);
                router.setRedirect();
            }else{
                request.setAttribute("register_msg", "Registration Failed");
                router = new Router(REGISTRATION_PAGE);
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return router;
    }
}
