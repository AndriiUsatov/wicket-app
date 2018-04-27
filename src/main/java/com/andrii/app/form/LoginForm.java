package com.andrii.app.form;

import com.andrii.app.page.user.UserListPage;
import com.andrii.app.util.SignInSession;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class LoginForm extends Form {


    public LoginForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("loginLabel", "Login: "));
        add(new Label("passwordLabel", "Password: "));
        add(new TextField("login", Model.of("")));
        add(new PasswordTextField("password", Model.of("")));
        add(new SubmitLink("submit", Model.of("")));
    }


    @Override
    public void onSubmit() {
        String login = get("login").getDefaultModelObjectAsString();
        String password = get("password").getDefaultModelObjectAsString();
        SignInSession session = (SignInSession) Session.get();
        if (session.signIn(login, password)){
            setResponsePage(UserListPage.class);
        }
    }
}
