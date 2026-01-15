package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);


    private static final String PAGE = "pages/start.jsp";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Use Logout Command");
        request.getSession().invalidate();
        Router router = new Router(request.getContextPath() + "/" + PAGE);
        //если поменять на forward то этот путь работать не будет "request.getContextPath() + "/" + PAGE"
        //и почему вообще не работает без request.getContextPath() и /
        router.setRedirect();
        return router;
    }
}
