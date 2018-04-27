package com.andrii.app.page.login;

import com.andrii.app.form.LoginForm;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class LoginPage extends WebPage {

	public LoginPage(final PageParameters parameters) {
		super(parameters);
		init();
//		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

    }

    private void init(){
		add(new LoginForm("loginForm"));
	}
}
