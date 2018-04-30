package com.andrii.app.dao;

import com.andrii.app.domain.User;

import java.util.List;

public interface UserDAO {

    void add(User user);

    void update(User user);

    void remove(User user);

    List<User> findByFullName(String firstName, String lastName, String middleName);

    List<User> findAll();

    Long getCount();

    User findByLogin(String login);

    User findByID(Long id);
}
