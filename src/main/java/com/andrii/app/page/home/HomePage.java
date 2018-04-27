package com.andrii.app.page.home;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {

    @Override
    protected void onConfigure() {
        super.onConfigure();

        AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
        if (!AuthenticatedWebSession.get().isSignedIn()) {
            app.restartResponseAtSignInPage();
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }
}
