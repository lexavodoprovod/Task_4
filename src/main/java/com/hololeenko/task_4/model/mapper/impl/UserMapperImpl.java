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
        User user = new User.UserBuilder()
                .setId(rs.getInt(ID))
                .setName(rs.getString(NAME))
                .setLogin(rs.getString(EMAIL))
                .setPassword(rs.getString(PASSWORD))
                .build();

        if(rs.getString(ROLE).equals(UserRole.ADMIN.name())){
            user.setAdminRole();
            logger.info("User \"{}\" has ADMIN status", user.getLogin());
        }
        logger.info("Successfully called UserMapperImpl for User: {}", user.getLogin());
        return user;
    }
}
