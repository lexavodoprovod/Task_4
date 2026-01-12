package com.hololeenko.task_4.model.dao;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.model.entity.AbstractEntity;
import com.hololeenko.task_4.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao<T extends AbstractEntity> {
    List<User> findAll() throws DaoException;
    Optional<User> findUserById(int id) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    boolean createUser(T user) throws DaoException;
    boolean updateUser(T user) throws DaoException;
}
