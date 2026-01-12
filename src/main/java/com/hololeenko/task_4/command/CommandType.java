package com.hololeenko.task_4.command;

import com.hololeenko.task_4.command.impl.AddUserCommand;
import com.hololeenko.task_4.command.impl.DefaultCommand;
import com.hololeenko.task_4.command.impl.LoginCommand;
import com.hololeenko.task_4.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }


    public static Command define(String commandStr){
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
