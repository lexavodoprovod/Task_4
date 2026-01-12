package com.hololeenko.task_4.model.service;

import com.hololeenko.task_4.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean register(String userName, String login, String password) throws ServiceException;
}
