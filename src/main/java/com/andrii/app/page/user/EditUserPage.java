package com.andrii.app.page.user;

import com.andrii.app.domain.User;
import com.andrii.app.form.EditUserForm;
import com.andrii.app.page.AuthenticatedWebPage;
import com.andrii.app.service.UserService;
import org.apache.wicket.markup.html.WebPage;

public class EditUserPage extends WebPage implements AuthenticatedWebPage {

    private static UserService userService = UserService.getUserServiceInstance();

    @Override
    protected void onInitialize() {
        super.onInitialize();
        User editUser = userService.getUserByID(Long.parseLong(getPageParameters().get("editUserID").toString()));
        add(new EditUserForm("editUserForm" ,editUser));
    }
}
