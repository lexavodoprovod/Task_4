package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    private static final String PAGE = "index.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        request.setAttribute("login_msg", "You have been logged out");
        return PAGE;
    }
}
