package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    private static final String INDEX_PAGE = "start.jsp";


    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(INDEX_PAGE);
    }
}
