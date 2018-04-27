package com.andrii.app.page.user;

import com.andrii.app.form.AddUserForm;
import com.andrii.app.page.AuthenticatedWebPage;
import org.apache.wicket.markup.html.WebPage;

public class AddUserPage extends WebPage implements AuthenticatedWebPage {
    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AddUserForm("addUserForm"));
    }
}
