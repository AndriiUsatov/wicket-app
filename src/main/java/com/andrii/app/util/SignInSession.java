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
        try {
            User byLogin = userService.getUserByLogin(login);
            if (user == null && login != null && password != null) {
                User input = User.builder()
                        .login(login)
                        .password(password)
                        .build();
                if (byLogin.equals(input)) {
                    user = byLogin;
                    return true;
                }
            }
            return user.equals(byLogin);
        } catch (Exception e) {
            return false;
        }
    }

    public User getUser() {
        return user;
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}
