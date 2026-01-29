package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);


    private static final String START_PAGE = "/";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Use Logout Command");
        request.getSession().invalidate();
        Router router = new Router(START_PAGE);
        router.setRedirect();
        return router;
    }
}
