package com.hololeenko.task_4.model.service.impl;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.dao.UserDao;
import com.hololeenko.task_4.model.dao.impl.UserDaoImpl;
import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.service.UserService;
import com.hololeenko.task_4.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final UserServiceImpl instance = new UserServiceImpl();

    private final UserDao userDao;

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }
    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {

        if(login.isEmpty() || password.isEmpty()){
            return Optional.empty();
        }

        Optional<User> optionalUser;

        try {
            optionalUser = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.error("Exception while trying to find user with login in authenticate\"{}\", Exception: {}", login, e.getMessage());
            throw new ServiceException(e);
        }

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            logger.info("User with login {} was found, user_name is {}", login, user.getName());
            String userPassword = user.getPassword();

            if(PasswordEncryptor.checkPassword(password, userPassword)){
                logger.info("Successfully logged in!");
                return optionalUser;
            }
            logger.error("Incorrect password!");

        }

        return Optional.empty();
    }



    @Override
    public boolean register(String userName, String login, String password) throws ServiceException {

        if(userName.isEmpty() || login.isEmpty() || password.isEmpty()){
            return false;
        }


        Optional<User> optionalUser;

        boolean success = false;

        try {
            optionalUser = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.error("Exception while trying to find user with login in register \"{}\", Exception: {}", login, e.getMessage());
            throw new ServiceException(e);
        }



        if(optionalUser.isEmpty()){
            String hashedPassword = PasswordEncryptor.encrypt(password);

            User user = new User.UserBuilder()
                    .setName(userName)
                    .setLogin(login)
                    .setPassword(hashedPassword)
                    .build();
            try {
                success = userDao.create(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

        logger.info(success ? "Register successful!" : "Register failed");

        return success;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users;

        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.error("Exception while trying to find all users, Exception: {}", e.getMessage());
            throw new ServiceException(e);
        }


        return users;
    }
}
