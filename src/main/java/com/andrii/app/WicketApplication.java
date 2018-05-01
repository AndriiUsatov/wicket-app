package com.andrii.app;

import com.andrii.app.page.home.HomePage;
import com.andrii.app.page.user.AddUserPage;
import com.andrii.app.page.user.EditUserPage;
import com.andrii.app.page.user.UserListPage;
import com.andrii.app.util.AppExceptionMapper;
import com.andrii.app.util.AppIProvider;
import com.andrii.app.util.AuthorizationStrategy;
import com.andrii.app.util.SignInSession;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IExceptionMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.IProvider;

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
		mountPage("/user", UserListPage.class);
		mountPage("/user/edit", EditUserPage.class);
		mountPage("/user/add", AddUserPage.class);


		getSecuritySettings().setAuthorizationStrategy(new AuthorizationStrategy());
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new SignInSession(request);
	}

	@Override
	public IProvider<IExceptionMapper> getExceptionMapperProvider() {
		return AppIProvider.getProviderInstance();
	}
}
