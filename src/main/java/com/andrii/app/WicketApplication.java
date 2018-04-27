package com.andrii.app;

import com.andrii.app.page.home.HomePage;
import com.andrii.app.page.login.LoginPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication
{

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}




	@Override
	public void init()
	{
		super.init();
		mountPage("/home", HomePage.class);
		mountPage("/login", LoginPage.class);

//		getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy()

	}



}
