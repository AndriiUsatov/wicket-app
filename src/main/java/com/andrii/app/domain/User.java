package com.andrii.app.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String mail;
}
