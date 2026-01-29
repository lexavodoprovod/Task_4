package com.hololeenko.task_4.command;

import com.hololeenko.task_4.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    GO_TO_REGISTRATION(new GoToRegistrationCommand()),
    SHOW_USERS(new ShowUsersCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr){
        if(commandStr == null){
            return DEFAULT.command;
        }
        switch(commandStr){
            case "add_user", "login", "logout", "default", "go_to_registration", "show_users" ->{
                CommandType current = CommandType.valueOf(commandStr.toUpperCase());
                return current.command;
            }
            default -> {
                return DEFAULT.command;
            }
        }

    }


//    public static Command define(String commandStr){

//
//        String realCommand = commandStr.toUpperCase();
//
//        switch (realCommand){
//            case ADD_USER, LOGIN, LOGOUT, DEFAULT,  GO_TO_REGISTRATION ->{
//                CommandType current = CommandType.valueOf(commandStr.toUpperCase());
//                return current.command;
//            } default ->{
//                return DEFAULT.command;
//            }
//        }
//
//    }
}
