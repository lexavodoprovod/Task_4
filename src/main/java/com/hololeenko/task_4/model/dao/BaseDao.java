package com.hololeenko.task_4.model.dao;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.model.entity.AbstractEntity;
import com.hololeenko.task_4.model.entity.User;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    List<User> findAll() throws DaoException;
    boolean create(T entity) throws DaoException;
    boolean update(T entity) throws DaoException;
}
