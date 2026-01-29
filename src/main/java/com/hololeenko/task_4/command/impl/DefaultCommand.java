package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import static com.hololeenko.task_4.command.ConstantPagesPath.*;


public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(START_PAGE);
    }
}
