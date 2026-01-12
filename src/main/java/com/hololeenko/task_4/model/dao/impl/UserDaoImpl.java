package com.hololeenko.task_4.model.dao.impl;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.model.dao.UserDao;
import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.mapper.impl.UserMapperImpl;
import com.hololeenko.task_4.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<User> {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final UserMapperImpl userMapper = new UserMapperImpl();

    private static UserDaoImpl instance = new UserDaoImpl();

    private static final String SQL_FIND_USER = "SELECT * FROM users WHERE user_email = ?";





    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }


    @Override
    public List<User> findAll() throws DaoException {
        return List.of();
    }

    @Override
    public Optional<User> findUserById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of(userMapper.map(resultSet));
                }
            }
        }catch (SQLException e){
            logger.error("Exception in findUserByLogin",e);
            throw new DaoException("SQL Error find user by login");
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public boolean createUser(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        return false;
    }
}
