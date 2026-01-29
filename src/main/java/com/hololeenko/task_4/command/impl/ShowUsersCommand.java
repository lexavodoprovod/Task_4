package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import com.hololeenko.task_4.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckAllUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("use CheckAllUsersCommand");


    }
}
