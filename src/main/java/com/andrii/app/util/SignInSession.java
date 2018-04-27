package com.andrii.app.util;

import com.andrii.app.domain.User;
import com.andrii.app.service.UserService;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class SignInSession extends AuthenticatedWebSession {

    private static UserService userService = UserService.getUserServiceInstance();
    private User user;

    public SignInSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String login, String password) {
        if(user == null || login != null && password != null){
            User tmp = userService.getUserByLogin(login);
            User input = User.builder()
                    .login(login)
                    .password(password)
                    .build();
            if(tmp.equals(input)){
                user = tmp;
                return true;
            }
        }
        return user.equals(userService.getUserByLogin(login));
    }

    public User getUser(){
        return user;
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}
