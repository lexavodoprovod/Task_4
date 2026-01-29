package com.hololeenko.task_4.model.service;

import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(String login, String password) throws ServiceException;
    boolean register(String userName, String login, String password) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
}
