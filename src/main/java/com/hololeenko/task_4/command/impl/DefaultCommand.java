package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    private static final String START_PAGE = "/";


    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(START_PAGE);
    }
}
