package com.andrii.app.util;

import com.andrii.app.page.AuthenticatedWebPage;
import com.andrii.app.page.home.HomePage;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

public class AuthorizationStrategy extends IAuthorizationStrategy.AllowAllAuthorizationStrategy{

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> c) {
            SignInSession session = (SignInSession) Session.get();
            if(AuthenticatedWebPage.class.isAssignableFrom(c)){
//                return session.get().isSignedIn();
                if(session.isSignedIn()){
                    return true;
                }
                throw new RestartResponseAtInterceptPageException(HomePage.class);
            }
        return true;
    }
}
