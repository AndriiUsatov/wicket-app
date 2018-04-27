package com.andrii.app.util;

import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

public class AuthorizationStrategy extends IAuthorizationStrategy.AllowAllAuthorizationStrategy{

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> c) {
//        if(AuthenticatedWebPage)
        return true;
    }
}
