package com.codegym.dao;

import java.util.*;
import java.sql.*;

import com.codegym.model.User;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;

    public User selectUser(int id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public User getUserById(int id);

    public void insertUserStore(User user) throws SQLException;

    public List<User> showListUsers();

    public boolean editUserProcedure(User user) throws SQLException;
}
