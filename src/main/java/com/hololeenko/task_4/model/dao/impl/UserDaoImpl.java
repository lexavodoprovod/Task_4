package com.hololeenko.task_4.model.dao.impl;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.model.dao.BaseDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao{

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final UserMapperImpl userMapper = new UserMapperImpl();

    private static UserDaoImpl instance = new UserDaoImpl();

    private static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE user_email = ?";

    private static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE user_id = ?";

    private static final String SQL_FIND_ALL_USERS =
            "SELECT * FROM users";

    private static final String SQL_CREATE_USER =
            "INSERT INTO users (user_name, user_email, user_password, user_role) VALUES (?, ?, ?, ?)";

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }


    @Override
    public List<User> findAll() throws DaoException {

        Connection connection = ConnectionPool.getInstance().getConnection();

        List<User> users = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    User user = userMapper.map(resultSet);
                    users.add(user);
                }
            }

        }catch(SQLException e){
            logger.error("Exception in findAll");
            throw new DaoException("SQl error findAllUsers", e);
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return users;
    }

    @Override
    public Optional<User> findUserById(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of(userMapper.map(resultSet));
                }
            }
        }catch (SQLException e){
            logger.error("Exception in findUserById");
            throw new DaoException("SQl error in findUserById", e);
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of(userMapper.map(resultSet));
                }
            }
        }catch (SQLException e){
            logger.error("Exception in findUserByLogin",e);
            throw new DaoException("SQL Error in findUserByLogin");
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public boolean create(User user) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)){
            logger.info("Use preparedStatement in createUser ");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toString());

            logger.info("This message before executeUpdate in createUser ");
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            logger.error("Exception in createUser",e);
            throw new DaoException("SQL Error in createUser");
        }finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return true;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }
}
