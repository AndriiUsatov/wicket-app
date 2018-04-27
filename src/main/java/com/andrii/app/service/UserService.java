package com.andrii.app.service;

import com.andrii.app.dao.UserDAO;
import com.andrii.app.dao.impl.UserDAOImpl;
import com.andrii.app.domain.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();


    public void addUser(User user) {
        userDAO.add(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void removeUser(User user) {
        userDAO.remove(user);
    }

    public List<User> getUserByFullName(String firstName, String lastName, String middleName) {
        return userDAO.findByFullName(firstName, lastName, middleName);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public Long getCountOfUsers() {
        return userDAO.getCount();
    }

    public Boolean userLoginExists(String login) {
        return userDAO.findByLogin(login) != null;
    }
}
