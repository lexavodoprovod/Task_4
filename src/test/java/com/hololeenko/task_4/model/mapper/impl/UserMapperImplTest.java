package com.hololeenko.task_4.model.mapper.impl;

import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.model.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserMapperImplTest {

    private static final String ID = "id";
    private static final String NAME = "user_name";
    private static final String EMAIL = "user_email";
    private static final String PASSWORD = "user_password";
    private static final String ROLE = "user_role";

    private UserMapperImpl mapper = new UserMapperImpl();
    private ResultSet rs;

    @BeforeEach
    void setUp(){
        rs = mock(ResultSet.class);
    }

    @Test
    @DisplayName("Create User with Result Set")
    void buildUser() throws SQLException {
        when(rs.getInt(ID)).thenReturn(10);
        when(rs.getString(NAME)).thenReturn("Nikita");
        when(rs.getString(EMAIL)).thenReturn("nikita@example.com");
        when(rs.getString(PASSWORD)).thenReturn("hashed_pass");
        when(rs.getString(ROLE)).thenReturn("USER");

        User result = mapper.map(rs);

        assertNotNull(result);
        assertEquals(10, result.getId());
        assertEquals("Nikita", result.getName());
        assertEquals("nikita@example.com", result.getLogin());
        assertEquals("hashed_pass", result.getPassword());
        assertEquals(UserRole.USER, result.getRole());
    }

    @Test
    @DisplayName(("Create Admin with Result Set"))
    void buildAdmin() throws SQLException {
        when(rs.getInt(ID)).thenReturn(10);
        when(rs.getString(NAME)).thenReturn("Nikita");
        when(rs.getString(EMAIL)).thenReturn("nikita@example.com");
        when(rs.getString(PASSWORD)).thenReturn("hashed_pass");
        when(rs.getString(ROLE)).thenReturn("ADMIN");

        User result = mapper.map(rs);

        assertNotNull(result);
        assertEquals(10, result.getId());
        assertEquals("Nikita", result.getName());
        assertEquals("nikita@example.com", result.getLogin());
        assertEquals("hashed_pass", result.getPassword());
        assertEquals(UserRole.ADMIN, result.getRole());
    }

}