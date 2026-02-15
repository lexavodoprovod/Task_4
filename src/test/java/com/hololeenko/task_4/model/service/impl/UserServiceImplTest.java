package com.hololeenko.task_4.model.service.impl;

import com.hololeenko.task_4.exception.DaoException;
import com.hololeenko.task_4.exception.ServiceException;
import com.hololeenko.task_4.model.dao.UserDao;
import com.hololeenko.task_4.model.entity.User;
import com.hololeenko.task_4.util.PasswordEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserDao userDaoMock;

    @BeforeEach
    void setUp() {
        userDaoMock = mock(UserDao.class);
        userService = new UserServiceImpl(userDaoMock);
    }

    @Test
    @DisplayName("Should authenticate user by login and password")
    void authenticateUser() throws DaoException, ServiceException {
        String login = "testUser";
        String password = "testPassword";
        String hashedPassword = PasswordEncryptor.encrypt(password);

        User user = new User.UserBuilder()
                .setId(1)
                .setName("TestUser")
                .setLogin(login)
                .setPassword(hashedPassword)
                .build();

        when(userDaoMock.findUserByLogin(login)).thenReturn(Optional.of(user));

        Optional<User> result = userService.authenticate(login, password);

        assertTrue(result.isPresent());
        assertEquals(login, result.get().getLogin());

    }

    @Test
    @DisplayName("Shouldn't authenticate user with incorrect login")
    void authenticateUserByInvalidLogin() throws DaoException, ServiceException {
        String realLogin = "testUser";
        String fakeLogin = "fakeTestUser";
        String password = "testPassword";
        String hashedPassword = PasswordEncryptor.encrypt(password);

        User user = new User.UserBuilder()
                .setId(1)
                .setName("TestUser")
                .setLogin(realLogin)
                .setPassword(hashedPassword)
                .build();

        when(userDaoMock.findUserByLogin(fakeLogin)).thenReturn(Optional.of(user));

        Optional<User> result = userService.authenticate(realLogin, password);

        assertTrue(result.isEmpty());

    }

    @Test
    @DisplayName("Shouldn't authenticate user with incorrect password")
    void authenticateUserByInvalidPassword() throws DaoException, ServiceException {
        String login = "testUser";
        String password = "testPassword";
        String hashedPassword = PasswordEncryptor.encrypt("realPassword");

        User user = new User.UserBuilder()
                .setId(1)
                .setName("TestUser")
                .setLogin(login)
                .setPassword(hashedPassword)
                .build();

        when(userDaoMock.findUserByLogin(login)).thenReturn(Optional.of(user));

        Optional<User> result = userService.authenticate(login, password);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return true after register user")
    void registerUser() throws DaoException, ServiceException {
        String userName = "TestUser";
        String login = "testUser";
        String password = "testPassword";

        when(userDaoMock.findUserByLogin(login)).thenReturn(Optional.empty());

        when(userDaoMock.create(any(User.class))).thenReturn(true);

        boolean result = userService.register(userName, login, password);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false because user already exist")
    void registerExistedUser() throws DaoException, ServiceException{
        String userName = "TestUser";
        String login = "testUser";
        String password = "testPassword";

        when(userDaoMock.findUserByLogin(login)).thenReturn(Optional.of(mock(User.class)));

        boolean result = userService.register(userName, login, password);

        verify(userDaoMock, never()).create(any(User.class));
        assertFalse(result);
    }

    @Test
    @DisplayName("Should get all users from database")
    void getAllUsers() throws DaoException, ServiceException{
        List<User> list = List.of(mock(User.class), mock(User.class));
        when(userDaoMock.findAll()).thenReturn(list);

        List<User> users = userService.getAllUsers();

        assertNotNull(users, "users List is null");
        assertEquals(2, users.size());
        assertEquals(list, users);

        verify(userDaoMock, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw ServiceException when DaoException occurs")
    void getAllUsersWithException() throws DaoException, ServiceException{
       when(userDaoMock.findAll()).thenThrow(new DaoException("Something wrong with db"));

       assertThrows(ServiceException.class, () -> userService.getAllUsers());
    }


}