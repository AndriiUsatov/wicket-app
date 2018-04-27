package com.andrii.app.dao.mapper;

import com.andrii.app.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User map(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .mail(rs.getString("mail"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .middleName(rs.getString("middle_name"))
                .password(rs.getString("password"))
                .build();
    }
}
