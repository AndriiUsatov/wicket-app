package com.andrii.app.page.error;

import com.andrii.app.page.home.HomePage;
import com.andrii.app.page.user.UserListPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class ErrorPage extends WebPage {
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Link("toHomePage") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });
        add(new Link("toUserListPage") {
            @Override
            public void onClick() {
                setResponsePage(UserListPage.class);
            }
        });
    }
}
