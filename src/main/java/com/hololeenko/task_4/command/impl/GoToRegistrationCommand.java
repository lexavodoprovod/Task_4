package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import com.hololeenko.task_4.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToRegistrationCommand implements Command {

    private static final String REGISTER_PAGE = "pages/register.jsp";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(REGISTER_PAGE);
    }
}
