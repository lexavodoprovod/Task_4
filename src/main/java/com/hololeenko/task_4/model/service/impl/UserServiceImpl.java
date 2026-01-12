package com.hololeenko.task_4.model.service.impl;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.dao.impl.UserDaoImpl;
import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }
    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();
        Optional<User> user = Optional.empty();
        try {
            user = userDaoImpl.findUserByLogin(login);
        } catch (DaoException e) {
            logger.error("Exception while trying to find user with login \"{}\", Exception: {}", login, e.getMessage());
            throw new ServiceException(e);
        }
        boolean success = false;

        if(user.isPresent()){
            User realUser = user.get();
            String userPassword = realUser.getPassword();
            if(userPassword.equals(password)){
                success = true;
            }
        }

        logger.info(success ? "Successfully logged in!" : "Invalid login or password");

        return success;
    }

    @Override
    public boolean register(String userName, String login, String password) {
        return false;
    }
}
