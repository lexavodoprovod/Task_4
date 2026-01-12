package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.exception.CommandException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.service.UserService;
import com.hololeenko.task_4.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String MAIN_PAGE = "pages/main.jsp";
    private static final String INDEX_PAGE = "index.jsp";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logger.info("Use LoginCommand");
        String login = request.getParameter("login");//Выносим в константы, а лучше в отдельный класс как все параметры request
        String password = request.getParameter("pass");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if(userService.authenticate(login, password)){
                request.setAttribute("user", login);
                session.setAttribute("user_login", login);
                page = MAIN_PAGE;
            }else{
                request.setAttribute("login_msg", "Invalid username or password");
                page = INDEX_PAGE;
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
