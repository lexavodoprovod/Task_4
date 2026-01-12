package com.hololeenko.task_4.model.mapper.impl;

import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.entity.UserRole;
import com.hololeenko.task_4.model.mapper.EntityMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperImpl implements EntityMapper<User> {

    private static final Logger logger = LogManager.getLogger(UserMapperImpl.class);

    private static final String ID = "id";
    private static final String NAME = "user_name";
    private static final String EMAIL = "user_email";
    private static final String PASSWORD = "user_password";
    private static final String ROLE = "user_role";

    @Override
    public User map(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setName(rs.getString(NAME));
        user.setLogin(rs.getString(EMAIL));
        user.setPassword(rs.getString(PASSWORD));
        user.setRole(UserRole.valueOf(rs.getString(ROLE)));
        logger.info("Successfully called UserMapperImpl for User: {}", user.getLogin());
        return user;
    }
}
