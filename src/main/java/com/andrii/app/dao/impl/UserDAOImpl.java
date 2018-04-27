package com.andrii.app.dao.impl;

import com.andrii.app.dao.UserDAO;
import com.andrii.app.dao.mapper.UserMapper;
import com.andrii.app.domain.User;
import com.andrii.app.exception.DAOException;
import com.andrii.app.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String ADD_USER = "INSERT INTO `user`(login, mail, first_name, last_name, middle_name, password)" +
            " VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE `user` SET login = ?, mail = ?, first_name = ?, last_name = ?, middle_name = ?, password = ? WHERE id = ?";
    private static final String REMOVE_USER = "DELETE FROM `user` WHERE id = ?";
    private static final String GET_BY_FULL_NAME = "SELECT * FROM user WHERE concat(first_name, last_name, middle_name)=?";
    private static final String GET_ALL = "SELECT * FROM `user`";
    private static final String COUNT = "SELECT count(*) FROM `user`";
    private static final String GET_BY_LOGIN = "SELECT * FROM `user` WHERE login=?";

    @Override
    public void add(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getMail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getMiddleName());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getMail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getMiddleName());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public void remove(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> findByFullName(String firstName, String lastName, String middleName) {
        List<User> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_FULL_NAME)) {
            preparedStatement.setString(1, firstName + lastName + middleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(UserMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                result.add(UserMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public Long getCount() {
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement()){
           return statement.executeQuery(COUNT).getLong(1);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        User result = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN)){
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                result = UserMapper.map(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
        return result;
    }

    private static Connection getConnection() {
        return connectionPool.getConnection();
    }
}
