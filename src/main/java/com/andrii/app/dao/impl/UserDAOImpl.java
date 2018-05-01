package com.andrii.app.dao.impl;

import com.andrii.app.dao.UserDAO;
import com.andrii.app.dao.mapper.UserMapper;
import com.andrii.app.domain.User;
import com.andrii.app.exception.DAOException;
import com.andrii.app.util.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Properties queries = new Properties();

    public UserDAOImpl(){
        try {
            queries.load(getClass().getResourceAsStream("/queries.properies"));
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("add_user"))) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("update_user"))) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("remove_user"))) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("get_user_by_full_name"))) {
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
            ResultSet resultSet = statement.executeQuery(queries.getProperty("get_all_users"));
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
           return statement.executeQuery(queries.getProperty("get_count_of_users")).getLong(1);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("get_user_by_login"))){
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return UserMapper.map(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public User findByID(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("get_user_by_id"))){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return UserMapper.map(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    private static Connection getConnection() {
        return connectionPool.getConnection();
    }
}
