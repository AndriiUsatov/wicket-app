package com.andrii.app.page.home;

import com.andrii.app.form.LoginForm;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		super(parameters);
    }

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new LoginForm("loginForm"));
	}



}
