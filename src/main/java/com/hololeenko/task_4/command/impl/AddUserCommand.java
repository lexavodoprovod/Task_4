package com.hololeenko.task_4.command.impl;

import com.hololeenko.task_4.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "";
    }
}
